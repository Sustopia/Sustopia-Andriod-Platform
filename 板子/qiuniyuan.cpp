#include<cstdio>
#include<iostream>
using namespace std;
int x,y;
void extend_Euclid(int a,int b)
{
	if(b==0)
	{
		x=1;
		y=0;
		return;
	}
	extend_Euclid(b,a%b);
	int t=x;
	x=y;
	y=t-a/b*y;
}
int main()
{
	int b,mod;
	while(cin>>b>>mod)
	{
		extend_Euclid(b,mod);
		cout<<(x%mod+mod)%mod<<endl;
	}
	return 0; 
}
