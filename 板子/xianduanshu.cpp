#include<cstdio>
using namespace std;
const int MAXN=50010;
int a[MAXN],ans[MAXN<<2],lazy[MAXN<<2];
void PushUp(int rt)
{
    ans[rt]=ans[rt<<1]+ans[rt<<1|1];
}
void PushDown(int rt,int ln,int rn)//ln表示左子树元素结点个数，rn表示右子树结点个数
{
    if (lazy[rt])
    {
        lazy[rt<<1]+=lazy[rt];
        lazy[rt<<1|1]+=lazy[rt];
        ans[rt<<1]+=lazy[rt]*ln;
        ans[rt<<1|1]+=lazy[rt]*rn;
        lazy[rt]=0;
    }
}
void Build(int l,int r,int rt)
{
    if(l==r)
    {
        ans[rt]=a[l];
        return;
    }
    int mid=(l+r)>>1;
    Build(l,mid,rt<<1);
    Build(mid+1,r,rt<<1|1);
    PushUp(rt);
}
void Add(int L,int C,int l,int r,int rt)
{
    if(l==r)
    {
        ans[rt]+=C;
        return;
    }
    int mid=(l+r)>>1;
    //PushDown(rt,mid-l+1,r-mid); 若既有点更新又有区间更新，需要这句话
    if(L<=mid)
    {
    	Add(L,C,l,mid,rt<<1);
	}
    else
    {
    	Add(L,C,mid+1,r,rt<<1|1);
	}
    PushUp(rt);
}
void Update(int L,int R,int C,int l,int r,int rt)
{
    if(L<=l&&r<=R)
    {
        ans[rt]+=C*(r-l+1);
        lazy[rt]+=C;
        return;
    }
    int mid=(l+r)>>1;
    PushDown(rt,mid-l+1,r-mid);
    if(L<=mid)
    {
    	Update(L,R,C,l,mid,rt<<1);
	}
    if(R>mid)
    {
    	Update(L,R,C,mid+1,r,rt<<1|1);
	}
    PushUp(rt);
}
long long Query(int L,int R,int l,int r,int rt)
{
    if(L<=l&&r<=R)
    {
    	return ans[rt];
	}
    int mid=(l+r)>>1;
    PushDown(rt,mid-l+1,r-mid);//若更新只有点更新，不需要这句
    long long ANS=0;
    if(L<=mid)
    {
    	ANS+=Query(L,R,l,mid,rt<<1);
	}
    if(R>mid)
    {
    	ANS+=Query(L,R,mid+1,r,rt<<1|1);
	}
    return ANS;
}
int main()
{
	
	return 0;
}
