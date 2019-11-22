#include<cstdio>
#include<cstring>
using namespace std;
const int N=10,mod=1;
int tmp[N][N];
void multi(int a[][N],int b[][N],int n)
{
	memset(tmp,0,sizeof(tmp));
	for(int i=0;i<n;i++)
	{
		for(int j=0;j<n;j++)
		{
			for(int k=0;k<n;k++)
			{
				tmp[i][j]=(tmp[i][j]+a[i][k]*b[k][j]%mod)%mod;
			}
		}
	}
	for(int i=0;i<n;i++)
	{
		for(int j=0;j<n;j++)
		{
			a[i][j]=tmp[i][j];
		}
	}
}
int res[N][N];
void Pow(int a[][N],int n)
{
	memset(res,0,sizeof(res));//n是幂，N是矩阵大小
	for(int i=0;i<N;i++)
	{
		res[i][i]=1;
	}
	while(n)
	{
		if(n&1)
		{
			multi(res,a,N);//res=res*a;复制直接在multi里面实现了；
		}
		multi(a,a,N);//a=a*a
		n>>=1;
	}
}
int main()
{
	
	return 0;
}
