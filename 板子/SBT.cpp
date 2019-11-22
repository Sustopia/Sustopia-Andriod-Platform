#include<cstdio>
using namespace std;
const int maxn=(int)1e5+10;
const int inf=0x3f3f3f3f;
struct sbt
{
	int l,r,s,key;
}tr[maxn];
int top,root;
void left_rot(int&x)
{
	int y=tr[x].r;
	tr[x].r=tr[y].l;
	tr[y].l=x;
	tr[y].s=tr[x].s;
	tr[x].s=tr[tr[x].l].s+tr[tr[x].r].s+1;
	x=y;
}
void right_rot(int&x)
{
	int y=tr[x].l;
	tr[x].l=tr[y].r;
	tr[y].r=x;
	tr[y].s=tr[x].s;
	tr[x].s=tr[tr[x].l].s+tr[tr[x].r].s+1;
	x=y;
}
void maintain(int&x,bool flag)
{
	if(flag==0)
	{
		if(tr[tr[tr[x].l].l].s>tr[tr[x].r].s)
		{
			right_rot(x);
		}
		else if(tr[tr[tr[x].l].r].s>tr[tr[x].r].s)
		{
			left_rot(tr[x].l);
			right_rot(x);
		}
		else
		{
			return;
		}
	}
	else
	{
		if(tr[tr[tr[x].r].r].s>tr[tr[x].l].s)
		{
			left_rot(x);
		}
		else if(tr[tr[tr[x].r].l].s>tr[tr[x].l].s)
		{
			right_rot(tr[x].r);
			left_rot(x);
		}
		else
		{
			return;
		}
	}
	maintain(tr[x].l,0);
	maintain(tr[x].r,1);
}
void insert(int&x,int key)
{
	if(x==0)
	{
		x=++top;
		tr[x].l=tr[x].r=0;
		tr[x].s=1;
		tr[x].key=key;
	}
	else
	{
		tr[x].s++;
		if(key<tr[x].key)
		{
			insert(tr[x].l,key);
		}
		else
		{
			insert(tr[x].r,key);
		}
		maintain(x,key>=tr[x].key);
	}
}
int del(int&p,int w)
{
	if(tr[p].key==w||(tr[p].l==0&&w<tr[p].key)||(tr[p].r==0&&w>tr[p].key))
	{
		int delnum=tr[p].key;
		if(tr[p].l==0||tr[p].r==0)
		{
			p=tr[p].l+tr[p].r;
		}
		else
		{
			tr[p].key=del(tr[p].l,inf);
		}
		return delnum;
	}
	if(w<tr[p].key)
	{
		return del(tr[p].l,w);
	}
	else
	{
		return del(tr[p].r,w);
	}
}
int remove(int&x,int key)
{
	int k;
	tr[x].s--;
	if(key==tr[x].key||(key<tr[x].key&&tr[x].l==0)||(key>tr[x].key&&tr[x].r==0))
	{
		k=tr[x].key;
		if(tr[x].l&&tr[x].r)
		{
			tr[x].key=remove(tr[x].l,tr[x].key+1);
		}
		else
		{
			x=tr[x].l+tr[x].r;
		}
	}
	else if(key>tr[x].key)
	{
		k=remove(tr[x].r,key);
	}
	else if(key<tr[x].key)
	{
		k=remove(tr[x].l,key);
	}
	return k;
}
int getmin()
{
	int x;
	for(x=root;tr[x].l;x=tr[x].l);
	return tr[x].key;
}
int getmax()
{
	int x;
	for(x=root;tr[x].r;x=tr[x].r);
	return tr[x].key;
}
int pred(int &x,int y,int key)
{
	if(x==0)
	{
		return y;
	}
	if(tr[x].key<key)
	{
		return pred(tr[x].r,x,key);
	}
	else
	{
		return pred(tr[x].l,y,key);
	}
}
int succ(int&x,int y,int key)
{
	if(x==0)
	{
		return y;
	}
	if(tr[x].key>key)
	{
		return succ(tr[x].l,x,key);
	}
	else
	{
		return succ(tr[x].r,y,key);
	}
}
int select(int&x,int k)
{
	int r=tr[tr[x].l].s+1;
	if(r==k)
	{
		return tr[x].key;
	}
	else if(r<k)
	{
		return select(tr[x].r,k-r);
	}
	else
	{
		return select(tr[x].l,k);
	}
}
int rank(int&x,int key)
{ //求第K小数的逆运算
	if(key<tr[x].key)
	{
		return rank(tr[x].l,key);
	}
	else if(key>tr[x].key)
	{
		return rank(tr[x].r,key);
	}
	else
	{
		return tr[tr[x].l].s+1;
	}
}
int main()
{
	return 0;
}
