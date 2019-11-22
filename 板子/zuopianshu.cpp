#include<cstdio>
#include<queue>
#include<algorithm>
using namespace std;
const int maxn=10001,inf=0x3f3f3f3f;
int tot,n;
struct node
{
	int val,lc,rc,dis,fa;
}tree[maxn];
int merge(int x,int y)
{
	if(x==0)
	{
		return y;
	}
	if(y==0)
	{
		return x;
	}
	if(tree[x].val>tree[y].val||(tree[x].val==tree[y].val&&x>y))//这里改成<就莫名不对
	{
		swap(x,y);
	}
	tree[x].rc=merge(tree[x].rc,y);
	tree[tree[x].rc].fa=x;
	if(tree[tree[x].rc].dis>tree[tree[x].lc].dis)
	{
   		swap(tree[x].rc,tree[x].lc);
	}
	tree[x].dis=tree[x].rc==0?0:tree[tree[x].rc].dis+1;
	return x;
}
int add(int val,int x)
{
	tree[tot].lc=tree[tot].rc=tree[tot].dis=0;
	tree[tot++].val=val;
	return merge(tot-1,x);
}
int del(int x)
{
	int l=tree[x].lc,r=tree[x].rc;
	tree[x].fa=tree[x].lc=tree[x].rc=tree[x].dis=0;
	tree[x].val=-inf;
	tree[l].fa=l,tree[r].fa=r;
	return merge(l,r);
}
int find(int i)
{
	while(tree[i].fa&&i!=tree[i].fa)//如果上面没改，第二个条件可以去掉
	{
		i=tree[i].fa;
	}
	return i;
}
int build()
{
	queue<int>q;
	for(int i=1;i<=n;i++)
	{
		q.push(i);
	}
	while(!q.empty())
	{
		if(q.size()==1)
		{
			break;
		}
        else
		{
			int x=q.front();
			q.pop();
			int y=q.front();
			q.pop();
			q.push(merge(x,y));
		}
	}
	int finally=q.front();
	q.pop();
	return finally;
}
int main()
{
	tot=0;
	scanf("%d",&n);
	return 0;
}
