#include<cstdio>
#include<algorithm>
using namespace std;
const int MAXN=10000;
int ch[MAXN+10][2],Fa[MAXN+10],rev[MAXN+10],que[MAXN+10];
bool isroot(int u)
{
	if(!Fa[u])
	{
		return 1;
	}
	return ch[Fa[u]][0]!=u&&ch[Fa[u]][1]!=u;
}
void Rotate(int u)
{
	bool d=ch[Fa[u]][1]==u;
	int x=Fa[u],y=Fa[x];
	Fa[u]=y;
	if(!isroot(x))
	{
		ch[y][ch[y][1] == x] = u;
	}
	ch[x][d]=ch[u][!d];
	Fa[ch[u][!d]]=x;
	ch[u][!d]=x;
	Fa[x]=u;
}
void push_down(int u)
{
	if(rev[u])
	{
		rev[ch[u][0]]^=1;
		swap(ch[ch[u][0]][0],ch[ch[u][0]][1]);
		rev[ch[u][1]]^=1;
		swap(ch[ch[u][1]][0],ch[ch[u][1]][1]);
		rev[u]^=1;
	}
}
void Splay(int u)
{
	int top = 0;
	que[++top] = u;
	for(int i=u;!isroot(i);i=Fa[i])
	{
		que[++top] = Fa[i];
	}
	top++;
	while(--top)
	{
		push_down(que[top]);
	}
	while(!isroot(u))
	{
		int x=Fa[u],y=Fa[x];
		if(!isroot(x))
		{
			if((ch[y][0]==x)^(ch[x][0]==u))
			{
				Rotate(x);
			}
			else
			{
				Rotate(u);
			}
		}
		Rotate(u);
	}
}
void Access(int u)
{
	int t=0;
	while(u)
	{
		Splay(u);
		ch[u][1]=t;
		t=u;
		u=Fa[u];
	}
}
void reset_root(int u)
{
	Access(u);
	Splay(u);
	rev[u]^=1;
	swap(ch[u][0],ch[u][1]);
}
void Link(int x,int y)
{
	reset_root(x);
	Fa[x]=y;
	Splay(x);
}
void Cut(int x,int y)
{
	reset_root(x);
	Access(y);
	Splay(y);
	ch[y][0]=Fa[x]=0;
}
int main()
{
	
	return 0;
}
