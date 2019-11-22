#include<cstdio>
#include<cstring>
#include<algorithm>
using namespace std;
int ll_to_bi(long long num,char*bi)
{
	int pos=0;
	while(num)
	{
		bi[pos++]=num%10;
		num/=10;
	}
	return pos;
}
int string_to_bi(char*num)
{
	int len=strlen(num);
	for(int i=0;i<len;i++)
	{
		num[i]-='0';
	}
	for(int i=0;i<len>>1;i++)
	{
		swap(num[i],num[len-i-1]);
	}
	return len;
}
void print_bi(char*bi,int len)
{
	for(int i=len-1;i>=0;i--)
	{
		putchar(bi[i]+'0');
	}
}
int proceed(char*num,int len)
{
	bool proceeded=false;
	for(int i=0;i<len-1;i++)
	{
		num[i+1]+=num[i]/10;
		num[i]%=10;
	}
	if(num[len-1]>9)
	{
		num[len]=num[len-1]/10;
		num[len-1]%=10;
		proceeded=true;
	}
	return proceeded?len+1:len;
}
int add(char*a1,int len1,char*a2,int len2,char*res)
{
	int min_len=min(len1,len2);
	int max_len=max(len1,len2);
	char*remain;
	if(min_len==len1)
	{
		remain=a2;
	}
	else
	{
		remain=a1;
	}
	for(int i=0;i<min_len;i++)
	{
		res[i]=a1[i]+a2[i];
	}
	for(int i=min_len;i<max_len;i++)
	{
		res[i]=remain[i];
	}
	return proceed(res,max_len);
}
int multiply(char*a1,int len1,char*a2,int len2,char*res)
{
	for(int i=0;i<len1+len2;i++)
	{
		res[i]=0;
	}
	int len=0;
	for(int i=0;i<len2;i++)
	{
		for(int j=0;j<len1;j++)
		{
			res[i+j]+=a1[j]*a2[i];
		}
		len=proceed(res,max(len,i+len1));
	}
	return len;
}
int power(char*num,int ori_len,long long times,char*res)
{
	res[0]=1;
	int len_res=1;
	char*base=new char[ori_len];
	int len_base=ori_len;
	for(int i=0;i<len_base;i++)
	{
		base[i]=num[i];
	}
	while(times)
	{
		if(times&1)
		{
			char*res_tmp=new char[len_res];
			for(int i=0;i<len_res;i++)
			{
				res_tmp[i]=res[i];
			}
			len_res=multiply(res_tmp,len_res,base,len_base,res);
			delete[]res_tmp;
		}
		char*base_tmp=new char[len_base];
		char*base_res=new char[len_base<<1];
		for(int i=0;i<len_base;i++)
		{
			base_tmp[i]=base[i];
		}
		len_base=multiply(base_tmp,len_base,base_tmp,len_base,base_res);
		delete[]base_tmp;
		delete[]base;
		base=base_res;
		times>>=1;
	}
	delete[]base;
	return len_res;
}
int main()
{
	char a1[1000],a2[1000],res[1000];
	long long times;
	scanf("%s%lld",a1,&times);
	int len1=string_to_bi(a1);
	int len=power(a1,len1,times,res);
	print_bi(res,len);
	printf("\n");
	return 0;
}
