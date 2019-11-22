#include<cstdio>
using namespace std;
// 可持久化线段树
const int N=100010;
struct Node
{
	int sum,lch,rch,lazy,origin;
	Node():sum(0),lch(-1),rch(-1),lazy(0),origin(-1){}
}tree[(N<<2)*4];
int tot,a[N],rt[N],curver;
void init()
{
	tot=0;
	curver=0;
}
int createIndentity(int p)
{    // 创建影子节点
	int cp=tot++;
	tree[cp]=Node();
	tree[cp].origin=p;
	tree[cp].sum=tree[p].sum;
	tree[cp].lazy=tree[p].lazy;
	return cp;
}
void pushup(int p)
{
	tree[p].sum=tree[tree[p].lch].sum+tree[tree[p].rch].sum;
}
void pushdown(int p,int l,int r,int m)
{
	int lch=tree[p].lch,rch=tree[p].rch;
	if(lch==-1||rch==-1)
	{
		int o=tree[p].origin;
		lch=tree[p].lch=createIndentity(tree[o].lch);
		rch=tree[p].rch=createIndentity(tree[o].rch);
	}
	tree[lch].lazy+=tree[p].lazy;
	tree[rch].lazy+=tree[p].lazy;
	tree[lch].sum+=tree[p].lazy*(m-l+1);
	tree[rch].sum+=tree[p].lazy*(r-m);
	tree[p].lazy=0;
}
int build(int l,int r)
{
	int p=tot++;
	tree[p]=Node();
	if(l==r)
	{
		tree[p].sum=a[l];
		return p;
	}
	int mid=(l+r)>>1;
	tree[p].lch=build(l,mid);
	tree[p].rch=build(mid+1,r);
	pushup(p);
	return p;
}
int add(int p,int l,int r,int x,int y,int z)
{
	int cp=tot++;       // create shadow node
	tree[cp]=Node();
	tree[cp].origin=p;  // origin node number, prepared for pushdown operation
	if(x<=l&&r<=y)
	{
		tree[cp].lazy=tree[p].lazy+z;
		tree[cp].sum=tree[p].sum+z*(r-l+1);
		return cp;
	}
	int mid=(l+r)>>1;
	if(tree[p].lazy)
	{
		pushdown(p,l,r,mid);
	}
	if(x<=mid)
	{
		tree[cp].lch=add(tree[p].lch,l,mid,x,y,z);
	}
	else
	{
		tree[cp].lch=tree[p].lch;
	}
	if(mid<y)
	{
		tree[cp].rch=add(tree[p].rch,mid+1,r,x,y,z);
	}
	else
	{
		tree[cp].rch=tree[p].rch;
	}
	pushup(cp);
	return cp;
}
int query(int p,int l,int r,int x,int y)
{
	if(x<=l&&r<=y)
	{
		return tree[p].sum;
	}
	int mid=l+r>>1,ret=0;
	if(tree[p].lazy)
	{
		pushdown(p,l,r,mid);
	}
	if(x<=mid)
	{
		ret+=query(tree[p].lch,l,mid,x,y);
	}
	if(mid<y)
	{
		ret+=query(tree[p].rch,mid+1,r,x,y);
	}
	return ret;
}
int main() {
    int n;
    scanf("%d", &n);
    for (int i=1;i<=n;++i) scanf("%d", a+i);
    // 
    init();
    rt[curver]=build(1,n);
    for (;;){
        int u,v,w;
        char q[3];
        printf("q/m:");
        scanf("%s", q);
        if (q[0]=='m') {
            printf("Please input u, v, w and we will add w to [u,v]: ");
            scanf("%d%d%d", &u, &v, &w);
            rt[curver+1]=add(rt[curver],1,n,u,v,w);
            ++curver;
            for (int i=1;i<=n;++i) {
                printf("%d ", query(rt[curver],1,n,i,i));
            }
            puts("");
        }else {
            printf("Please input ver: ");
            scanf("%d", &w);
            for (int i=1;i<=n;++i) {
                printf("%d ", query(rt[w],1,n,i,i));
            }
            puts("");
        }
    }
    return 0;
}
