//模拟FIFO(First In First Out)页面置换算法
//第二次机会页面置换算法
//时钟页面置换算法
//这几个算法由于物理内存是链表实现，因此放在一起

#include <iostream>
#include <cstdlib>
#include <string>
#include <ctime>
#include <fstream>

using namespace std;

#define VM_SIZE 256		//虚拟内存大小
#define PHY_SIZE 128		//物理内存大小

//测试用变量
int testNum = 3000;	//指令执行次数
int interruptNum = 0;			//缺页中断次数
int writeBackNum = 0;		//写回磁盘次数
bool printText = false;

typedef struct	FrameTableNode		//物理内存页框
{
	int content;			//内容
	bool M;					//Modified
	bool R;					//Read
	FrameTableNode *next;	//指向下一个Frame

}FrameTable;

typedef struct			//页表结构体
{
	bool R;		//Read
	bool M;		//Modified
	bool exist;				//是否指向页框
	bool protection;	//保护位
	FrameTable *frame;		//指向页框的指针
	int *diskAddr;		//该页面对应的硬盘地址（这里虚拟一下，用个地址代替）

}PageTable;

PageTable pageTable[VM_SIZE];
//FrameTable frameTable[PHY_SIZE];

FrameTable *headFrame;	//指向链表头
FrameTable *tailFrame;		//指向链表尾


FrameTable *getIdleFrameAddress()			//获取闲置的页框
{

	while( headFrame ->R == true){
		headFrame->R = false;
		
		//将链表头放到链表尾部
		tailFrame->next = headFrame;
		tailFrame = headFrame;
		headFrame = headFrame->next;
		tailFrame->next = NULL;
	}
	//如果页框数据是dirty，则将页框的数据写回到磁盘
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
				
	//将链表头放到链表尾部
	tailFrame->next = headFrame;
	tailFrame = headFrame;
	headFrame = headFrame->next;
	tailFrame->next = NULL;

	return tailFrame;
}

FrameTable *loadIntoMemory( int address ) //将磁盘的数据加载到内存, 返回数据所在的物理内存地址
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

int readMemory( int address )		//获取虚拟内存某处的内容
{
	FrameTable *frame;
	if(pageTable[address].exist == false)	//页面不在内存中，引发缺页中断
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

int writeMemory( int content, int address )		//修改某个内存地址的内容为content
{
	FrameTable *frame;
	if( pageTable[address].exist == false)	 //如果不在物理内存中，先加载
	{
		if(printText) cout<<"Missing page interruption!  Address: "<<address<<endl;
		interruptNum ++;
		frame = loadIntoMemory(address);

	}else
	{
		frame = pageTable[address].frame;
	}

	//由于改变了content，页面设为dirty
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

//模拟实际情况下的内存使用
//%80的时间使用%20的内存
//%20的时间使用另外%80的内存
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
	cout<<"请输入命令执行次数："<<endl;
	cin>>testNum;
	int times = testNum;

	//随机进行读取和写入操作
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
	cout<<"测试次数: "<<testNum<<endl;
	cout<<"缺页中断次数"<<interruptNum<<endl;
	cout<<"写回磁盘次数"<<writeBackNum<<endl;
	cout<<"输出结果在log.txt中"<<endl;

	
	ofstream file;
	file.open("log.txt");
	file<<"page\tRead\tModified\tFrame\tContent\n";
	for(int i=0; i<VM_SIZE; i++)
	{
		file<<i<<"\t"<<pageTable[i].R<<"\t"<<pageTable[i].M<<"\t"<<pageTable[i].frame<<"\t"<<*(pageTable[i].diskAddr)<<endl;
	}
	file<<"测试次数: "<<testNum<<endl;
	file<<"缺页中断次数"<<interruptNum<<endl;
	file<<"写回磁盘次数"<<writeBackNum<<endl;
	return 0;
}