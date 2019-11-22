#include<cstdio>
#include<cstring>
using namespace std;
const int maxn=300;
const int maxN=300;
char t[maxN],p[maxn];
int next[maxn];
void CalcNext()
{
	int pLen=strlen(p);
	next[0]=-1;
	int k=-1,j=0;
	while(j<pLen)
	{
		if(k==-1||p[k]==p[j])
		{
			j++;
			k++;
			if(k!=-1&&p[j]==p[k])
			{
				next[j]=next[k];
			}
			else
			{
				next[j]=k;//No if and be this!!!
			}
		}
		else
		{
			k=next[k];
		}
	}
}
int KMP()
{
	int tLen=strlen(t),pLen=strlen(p);
	int ans=-1;
	int i=0,j=0;
	while(i<tLen)
	{
		if(j==-1||t[i]==p[j])
		{
			i++;
			j++;
		}
		else
		{
			j=next[j];
		}
		if(j==pLen)
		{
			ans=i-pLen;
			break;
		}
	}
	return ans;
}
int main()
{
	scanf("%s%s",t,p);
	CalcNext();
	printf("%d\n",KMP());
	return 0;
}
