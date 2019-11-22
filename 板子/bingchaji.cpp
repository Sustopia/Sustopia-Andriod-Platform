#include<cstdio>
using namespace std;
const int maxn=1001;
int par[maxn],Rank[maxn];
void makeSet(int size)
{
	for(int i=1;i<=size;i++)
	{
		par[i]=i;
		Rank[i]=0;
	}
}
int find(int x)
{
	int k,j,r;
	r=x;
	while(r!=par[r])
	{
		r=par[r];
	}
	k=x;
	while(k!=r)
	{
		j=par[k];
		par[k]=r;
		k=j;
	}
	return r;
}
void unite(int x,int y)
{
	x=find(x);
	y=find(y);
	if(x==y)
	{
		return;
	}
	if(Rank[x]<Rank[y])
	{
		par[x]=y;
	}
	else
	{
		par[y]=x;
		if(Rank[x]==Rank[y])
		{
			Rank[x]++;
		}
	}
}
int main()
{
	return 0;
}
