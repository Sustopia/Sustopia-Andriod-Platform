#include<cstdio>
using namespace std;
const int N=200010;
struct Node
{
	int num,lch,rch,origin;
	long long sum;
	Node():num(0),sum(0),lch(-1),rch(-1),origin(-1){}
}tree[N*40];
int tot,a[N],rt[N],curver,qnum;
long long pre_sum[N],qsum;
void init()
{
	tot=0;
	curver=0;
}
int createIndentity(int p)
{
	int cp=tot++;
	tree[cp]=Node();
	tree[cp].origin=p;
	tree[cp].num=tree[p].num;
	tree[cp].sum=tree[p].sum;
	return cp;
}
void pushup(int p)
{
	tree[p].sum=tree[tree[p].lch].sum+tree[tree[p].rch].sum;
	tree[p].num=tree[tree[p].lch].num+tree[tree[p].rch].num;
}
int build(int l,int r)
{
	int p=tot++;
	tree[p]=Node();
	if(l==r)
	{
		tree[p].sum=tree[p].num=0;
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
	int cp=tot++;
	tree[cp]=Node();
	tree[cp].origin=p;
	if(x<=l&&r<=y)
	{
		tree[cp].sum=tree[p].sum+z*(r-l+1);
		tree[cp].num=tree[p].num+r-l+1;
		return cp;
	}
	int mid=(l+r)>>1;
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
void query(int x,int y,int l,int r,int k)
{
	if(l>k)
	{
		return;
	}
	if(r<=k)
	{
		qsum+=tree[y].sum-tree[x].sum;
		qnum+=tree[y].num-tree[x].num;
		return;
	}
	int mid=(l+r)>>1;
	query(tree[x].lch,tree[y].lch,l,mid,k);
	query(tree[x].rch,tree[y].rch,mid+1,r,k);
}
int main()
{
	init();
	int n,q;
	scanf("%d%d",&n,&q);
	rt[0]=build(1,N);
	for(int i=1;i<=n;i++)
	{
		scanf("%d",&a[i]);
		pre_sum[i]=pre_sum[i-1]+a[i];
		rt[i]=add(rt[i-1],1,N,a[i],a[i],a[i]);
	}
	while(q--)
	{
		int l,r,x,y;
		scanf("%d%d%d%d",&l,&r,&x,&y);
		double l1=0.0,r1=100000.0,eps=1e-10;
		while(r1-l1>eps)
		{
			double mid=(l1+r1)/2;
			qsum=0,qnum=0;
			query(rt[l-1],rt[r],1,N,(int)mid);
			int num=(r-l+1)-qnum;
			double last=qsum+mid*num;
			double now=((double)pre_sum[r]-pre_sum[l-1])/y*(y-x);
			if(last<now)
			{
				l1=mid;
			}
			else
			{
				r1=mid;
			}
		}
		printf("%.15lf\n",l1);
	}
	return 0;
}
