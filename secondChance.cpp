//ģ��FIFO(First In First Out)ҳ���û��㷨
//�ڶ��λ���ҳ���û��㷨
//ʱ��ҳ���û��㷨
//�⼸���㷨���������ڴ�������ʵ�֣���˷���һ��

#include <iostream>
#include <cstdlib>
#include <string>
#include <ctime>
#include <fstream>

using namespace std;

#define VM_SIZE 256		//�����ڴ��С
#define PHY_SIZE 128		//�����ڴ��С

//�����ñ���
int testNum = 3000;	//ָ��ִ�д���
int interruptNum = 0;			//ȱҳ�жϴ���
int writeBackNum = 0;		//д�ش��̴���
bool printText = false;

typedef struct	FrameTableNode		//�����ڴ�ҳ��
{
	int content;			//����
	bool M;					//Modified
	bool R;					//Read
	FrameTableNode *next;	//ָ����һ��Frame

}FrameTable;

typedef struct			//ҳ��ṹ��
{
	bool R;		//Read
	bool M;		//Modified
	bool exist;				//�Ƿ�ָ��ҳ��
	bool protection;	//����λ
	FrameTable *frame;		//ָ��ҳ���ָ��
	int *diskAddr;		//��ҳ���Ӧ��Ӳ�̵�ַ����������һ�£��ø���ַ���棩

}PageTable;

PageTable pageTable[VM_SIZE];
//FrameTable frameTable[PHY_SIZE];

FrameTable *headFrame;	//ָ������ͷ
FrameTable *tailFrame;		//ָ������β


FrameTable *getIdleFrameAddress()			//��ȡ���õ�ҳ��
{

	while( headFrame ->R == true){
		headFrame->R = false;
		
		//������ͷ�ŵ�����β��
		tailFrame->next = headFrame;
		tailFrame = headFrame;
		headFrame = headFrame->next;
		tailFrame->next = NULL;
	}
	//���ҳ��������dirty����ҳ�������д�ص�����
	for(int i=0; i<VM_SIZE; i++)
	{
		if(pageTable[i].frame == headFrame)
		{
			if(headFrame->M)
			{
				*(pageTable[i].diskAddr) = headFrame->content;
				if(printText) cout<<"Writing back to disk : diskAddr: "<<pageTable[i].diskAddr<<endl;
				writeBackNum ++;
			}
			pageTable[i].exist = false;
			pageTable[i].frame = NULL;
			pageTable[i].R = false;
			pageTable[i].M = false;

		}
	}

	headFrame->R = false;
	headFrame->M = false;
				
	//������ͷ�ŵ�����β��
	tailFrame->next = headFrame;
	tailFrame = headFrame;
	headFrame = headFrame->next;
	tailFrame->next = NULL;

	return tailFrame;
}

FrameTable *loadIntoMemory( int address ) //�����̵����ݼ��ص��ڴ�, �����������ڵ������ڴ��ַ
{
	FrameTable *frame = getIdleFrameAddress();		
	frame->content = *(pageTable[address].diskAddr);
	frame->R = true;
	frame->M = false;
	
	pageTable[address].frame = frame;
	pageTable[address].exist = true;
	pageTable[address].R = true;
	pageTable[address].M = false;

	return frame;
}

int readMemory( int address )		//��ȡ�����ڴ�ĳ��������
{
	FrameTable *frame;
	if(pageTable[address].exist == false)	//ҳ�治���ڴ��У�����ȱҳ�ж�
	{
		if(printText) cout<<"Missing page interruption!  Address: "<<address<<endl;
		interruptNum ++;
		frame = loadIntoMemory(address);
	}else
	{
		frame = pageTable[address].frame;
	}

	int retContent = frame->content;

	if(printText) cout<<"Get content succeed: Address: "<<address<<" Content:  "<<retContent<<endl;
	return retContent;
}

int writeMemory( int content, int address )		//�޸�ĳ���ڴ��ַ������Ϊcontent
{
	FrameTable *frame;
	if( pageTable[address].exist == false)	 //������������ڴ��У��ȼ���
	{
		if(printText) cout<<"Missing page interruption!  Address: "<<address<<endl;
		interruptNum ++;
		frame = loadIntoMemory(address);

	}else
	{
		frame = pageTable[address].frame;
	}

	//���ڸı���content��ҳ����Ϊdirty
	pageTable[address].R = true;
	pageTable[address].M = true;

	frame->content = content;
	frame->R = true;
	frame->M = true;
	
	if(printText) cout<<"Write into memory: Address: "<<address<<" Content: "<<content<<endl;
	return 0;
}


void initMemory()
{
	for(int i=0; i<VM_SIZE; i++)
	{
		pageTable[i].frame = NULL;
		pageTable[i].protection = false;
		pageTable[i].exist = false;
		pageTable[i].M = false;
		pageTable[i].R = false;

		pageTable[i].diskAddr = new int(rand());
	}
	for(int i=0; i<PHY_SIZE; i++)
	{

		FrameTable * frame = new FrameTable();
		frame->content = -1;
		frame->M = true;
		frame->R = true;
		frame->next = headFrame;

		if( i == 0) tailFrame = frame;
		headFrame = frame;
	}
	
}

int getLevel(bool M, bool R)
{
	if(M && R) return 3;
	if(!M && !R) return 0;
	if( !M && R)  return 2;
	if( M && !R ) return 1;
	return 1;
}

//ģ��ʵ������µ��ڴ�ʹ��
//%80��ʱ��ʹ��%20���ڴ�
//%20��ʱ��ʹ������%80���ڴ�
int amitate( int top )
{
	int  hot = top * 2  / 10;
	int cold = top - hot;
	switch( rand()%10 )
	{
	case 1:case 2:
	case 3:case 4:
	case 5:case 6:
	case 7:case 0:
		return rand()%hot;
	case 8:
	case 9:
		return rand()%cold + hot;
	default:
		break;
	}
	return 0;
}


int main()
{
	srand((unsigned int)time(NULL));
	initMemory();
	cout<<"����������ִ�д�����"<<endl;
	cin>>testNum;
	int times = testNum;

	//������ж�ȡ��д�����
	while(times -- )
	{
		switch(rand()%2)
		{
		case 0:
			readMemory( amitate(VM_SIZE) );
			break;
		case 1:
			writeMemory(rand(), amitate(VM_SIZE));
			break;
		default:
			break;
		}
	}
	cout<<"���Դ���: "<<testNum<<endl;
	cout<<"ȱҳ�жϴ���"<<interruptNum<<endl;
	cout<<"д�ش��̴���"<<writeBackNum<<endl;
	cout<<"��������log.txt��"<<endl;

	
	ofstream file;
	file.open("log.txt");
	file<<"page\tRead\tModified\tFrame\tContent\n";
	for(int i=0; i<VM_SIZE; i++)
	{
		file<<i<<"\t"<<pageTable[i].R<<"\t"<<pageTable[i].M<<"\t"<<pageTable[i].frame<<"\t"<<*(pageTable[i].diskAddr)<<endl;
	}
	file<<"���Դ���: "<<testNum<<endl;
	file<<"ȱҳ�жϴ���"<<interruptNum<<endl;
	file<<"д�ش��̴���"<<writeBackNum<<endl;
	return 0;
}