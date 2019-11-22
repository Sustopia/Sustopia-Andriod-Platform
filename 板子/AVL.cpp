#include<cstdio>
#define Max(a,b) (a)<(b)?(b):(a);
using namespace std;
const int maxn=(int)1e5+10;
int pos;
struct node
{
	int lc,rc,h,v;
}tree[maxn];
int right_rotate(int r)
{
	int t=tree[r].lc;
	tree[r].lc=tree[t].rc;
	tree[t].rc=r;
	tree[r].h=Max(tree[tree[r].lc].h,tree[tree[r].rc].h)+1;
	tree[t].h=Max(tree[tree[t].lc].h,tree[tree[t].rc].h)+1;
	return t;
}
int left_rotate(int r)
{
	int t=tree[r].rc;
	tree[r].rc=tree[t].lc;
	tree[t].lc=r;
	tree[r].h=Max(tree[tree[r].lc].h,tree[tree[r].rc].h)+1;
	tree[t].h=Max(tree[tree[t].lc].h,tree[tree[t].rc].h)+1;
	return t;
}
int right_left_rotate(int r)
{
	tree[r].rc=right_rotate(tree[r].rc);
	return left_rotate(r);
}
int left_right_rotate(int r)
{
	tree[r].lc=left_rotate(tree[r].lc);
	return right_rotate(r);
}
void maintain(int&r)
{
	if(tree[tree[r].lc].h==tree[tree[r].rc].h+2)
	{
		int t=tree[r].lc;
		if(tree[tree[t].lc].h==tree[tree[r].rc].h+1)
		{
			r=right_rotate(r);
		}
		else if(tree[tree[t].rc].h==tree[tree[r].rc].h+1)
		{
			r=left_right_rotate(r);
		}	
	}
	else if(tree[tree[r].rc].h==tree[tree[r].lc].h+2)
	{
		int t=tree[r].rc;
		if(tree[tree[t].rc].h==tree[tree[r].lc].h+1)
		{
			r=left_rotate(r);
		}
		else if(tree[tree[t].lc].h==tree[tree[r].lc].h+1)
		{
			r=right_left_rotate(r);
		}
	}
	tree[r].h=Max(tree[tree[r].lc].h,tree[tree[r].rc].h)+1;
}
int insert(int r,int x)
{
	if(r==0)
	{
		tree[++pos].h=1;
		tree[pos].v=x;
		return pos;
	}
	if(x<tree[r].v)
	{
		tree[r].lc=insert(tree[r].lc,x);
	}
	else if(x>tree[r].v)
	{
		tree[r].rc=insert(tree[r].rc,x);
	}
	maintain(r);
	return r;
}
int delt(int&r,int x)
{
    int tx;
    if(x==tree[r].v||(x<tree[r].v&&tree[r].lc==0)||(x>tree[r].v&&tree[r].rc==0))
    {
        if(tree[r].lc==0||tree[r].rc==0)
        {
            tx=tree[r].v;
            r=tree[r].lc+tree[r].rc;
            return tx;
        }
        else
		{
        	tree[r].v=delt(tree[r].lc,x);
		}
    }
    else
    {
        if(x<tree[r].v)
		{
        	tx=delt(tree[r].lc,x);
		}
        else
		{
        	tx=delt(tree[r].rc,x);
		}
    }
    maintain(r);
    return tx;
}
int main()
{
	return 0;
}
