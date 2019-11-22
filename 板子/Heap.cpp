#include<cstdio>
using namespace std;
const int maxn=1048576;
int n,HeapSize,Heap[maxn+1];
void HeapUp(int p)
{
	int q=p>>1,a=Heap[p];
	while(q)
	{
		if(a<Heap[q])
		{
			Heap[p]=Heap[q];
		}
		else
		{
			break;
		}
		p=q;
		q=p>>1;
	}
	Heap[p]=a;
}
void AddToHeap(int a)
{
	Heap[++HeapSize]=a;
	HeapUp(HeapSize);
}
void HeapDown(int p)
{
	int q=p<<1,a=Heap[p];
	while(q<=HeapSize)
	{
		if(q<HeapSize&&Heap[q+1]<Heap[q])
		{
			q++;
		}
		if(Heap[q]<a)
		{
			Heap[p]=Heap[q];
		}
		else
		{
			break;
		}
		p=q;
		q=p<<1;
	}
	Heap[p]=a;
}
int GetTopFromHeap()
{
	int TopElement=Heap[1];
	Heap[1]=Heap[HeapSize--];
	HeapDown(1);
	return TopElement;
}
void BuildHeap()
{
	for(int i=HeapSize;i>0;i--)
	{
		HeapDown(i);
	}
}
int main()
{
	
	return 0;
}
