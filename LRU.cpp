//ģ��LRUҳ���û��㷨
//ʹ�õ���aging�㷨ģ��LRU

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

typedef struct			//ҳ��ṹ��
{
	bool R;		//Read
	bool M;		//Modified
	bool exist;				//�Ƿ�ָ��ҳ��
	bool protection;	//����λ
	int frameNum;		//ҳ��ţ��ݶ�Ϊҳ��������±꣩
	int *diskAddr;		//��ҳ���Ӧ��Ӳ�̵�ַ����������һ�£��ø���ַ���棩
}PageTable;

typedef struct			//�����ڴ�ҳ��
{
	int content;			//����
	int age;					//ҳ���ϻ��̶� 32λ

}FrameTable;

PageTable pageTable[VM_SIZE];
FrameTable frameTable[PHY_SIZE];

int getLevel(bool M, bool R);


int getIdleFrameAddress()			//��ȡ��"aging"��ҳ��
{

	int retAddr = 0;
	int tmpAge = 0x7FFFFFFF;
	//cout<<tmpAge<<endl;
	for(int i=0; i<PHY_SIZE; i++)
	{
		if( frameTable[i].age < tmpAge){
			retAddr = i;
			tmpAge = frameTable[i].age;
		}
	}

	frameTable[retAddr].age >>= 1;
	frameTable[retAddr].age += 0x40000000;
	cout<<retAddr<<endl;
	//���ҳ��������dirty����ҳ�������д�ص�����
	for(int i=0; i<VM_SIZE; i++)
	{
		if(pageTable[i].frameNum == retAddr)
		{
			if(pageTable[i].M)
			{
				*(pageTable[i].diskAddr) = frameTable[retAddr].content;
				if(printText) cout<<"Writing back to disk : diskAddr: "<<pageTable[i].diskAddr<<endl;
				writeBackNum ++;
			}
			pageTable[i].exist = false;
			pageTable[i].frameNum = -1;
			pageTable[i].R = false;
			pageTable[i].M = false;
			break;
		}
	}
	return retAddr;
}

int loadIntoMemory( int address ) //�����̵����ݼ��ص��ڴ�, �����������ڵ������ڴ��ַ
{
	int phyAddr = getIdleFrameAddress();		
	frameTable[phyAddr].content = *(pageTable[address].diskAddr);
	frameTable[phyAddr].age >>= 1;
	frameTable[phyAddr].age += 0x40000000;

	pageTable[address].frameNum = phyAddr;
	pageTable[address].exist = true;
	pageTable[address].R = true;
	pageTable[address].M = false;

	return phyAddr;
}

int readMemory( int address )		//��ȡ�����ڴ�ĳ��������
{
	int phyAddr;
	if(pageTable[address].exist == false)	//ҳ�治���ڴ��У�����ȱҳ�ж�
	{
		if(printText) cout<<"Missing page interruption!  Address: "<<address<<endl;
		interruptNum ++;
		phyAddr = loadIntoMemory(address);
	}else
	{
		phyAddr = pageTable[address].frameNum;
	}

	int retContent = frameTable[phyAddr].content;

	if(printText) cout<<"Get content succeed: Address: "<<address<<" Content:  "<<retContent<<endl;
	return retContent;
}

int writeMemory( int content, int address )		//�޸�ĳ���ڴ��ַ������Ϊcontent
{
	int phyAddr;
	if( pageTable[address].exist == false)	 //������������ڴ��У��ȼ���
	{
		if(printText) cout<<"Missing page interruption!  Address: "<<address<<endl;
		interruptNum ++;
		phyAddr = loadIntoMemory(address);

	}else
	{
		phyAddr = pageTable[address].frameNum;
	}

	//���ڸı���content��ҳ����Ϊdirty
	pageTable[address].R = true;
	pageTable[address].M = true;

	frameTable[phyAddr].content = content;

	if(printText) cout<<"Write into memory: Address: "<<address<<" Content: "<<content<<endl;
	return 0;
}


void initMemory()
{
	for(int i=0; i<VM_SIZE; i++)
	{
		pageTable[i].frameNum = -1;
		pageTable[i].protection = false;
		pageTable[i].exist = false;
		pageTable[i].M = false;
		pageTable[i].R = false;

		pageTable[i].diskAddr = new int(rand());
	}
	for(int i=0; i<PHY_SIZE; i++)
	{
		frameTable[i].content = 0;
		frameTable[i].age = 0;
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
	int count = 0;
	//������ж�ȡ��д�����
	while(times -- )
	{
		switch(rand()%4)
		{
		case 0:
		case 1:
		case 2:
			readMemory( rand()%256 );
			break;
		case 3:
			writeMemory(rand(), rand()%256);
			break;
		default:
			break;
		}
		count ++;
		count  %= 10;
		if(count == 0)
		{
			for(int i=0; i<PHY_SIZE; i++)
			{
				frameTable[i].age >>= 1;
			}
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
		file<<i<<"\t"<<pageTable[i].R<<"\t"<<pageTable[i].M<<"\t"<<pageTable[i].frameNum<<"\t"<<*(pageTable[i].diskAddr);
		if(pageTable[i].frameNum >= 0)
			file<<hex<<"\t"<<frameTable[pageTable[i].frameNum].age;
		file<<dec<<"\n";
	}

	cout<<dec<<0x40000000<<endl;
	cin>>times;

	file<<"���Դ���: "<<testNum<<endl;
	file<<"ȱҳ�жϴ���"<<interruptNum<<endl;
	file<<"д�ش��̴���"<<writeBackNum<<endl;
	return 0;
}