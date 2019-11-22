#include<cstdio>
#include<cstring>
#include<queue>
using namespace std;
const int maxn=(int)1<<20;
const int alphabet_size=26;
int tot,root;
struct node
{
	int count,children[alphabet_size],fail;
	bool exsit;
}tree[maxn];
bool vis[maxn];
int create_trie_node()
{
	tree[tot].count=0;
	tree[tot].exsit=false;
	tree[tot].fail=-1;
	memset(tree[tot].children,-1,sizeof(tree[tot].children));
	tot++;
	return tot-1;
}
void init()
{
	memset(vis,false,sizeof(vis));
	tot=0;
	root=create_trie_node();
}
void trie_insert(int rot,char key[])
{
	int pos=0;
	while(key[pos]!='\0')
	{
		if(tree[rot].children[key[pos]-'a']==-1)
		{
			tree[rot].children[key[pos]-'a']=create_trie_node();
		}
		rot=tree[rot].children[key[pos]-'a'];
		pos++;
	}
	tree[rot].exsit=true;
	tree[rot].count++;
}
void get_fail()
{
	queue<int>q;
	tree[root].fail=root;
	for(int i=0;i<alphabet_size;i++)
	{
		if(tree[root].children[i]!=-1)
		{
			tree[tree[root].children[i]].fail=root;
			q.push(tree[root].children[i]);
		}
		else
		{
			tree[root].children[i]=root;
		}
	}
	while(!q.empty())
	{
		int cr=q.front();
		q.pop();
		for(int i=0;i<alphabet_size;i++)
		{
			if(tree[cr].children[i]!=-1)
			{
				tree[tree[cr].children[i]].fail=tree[tree[cr].fail].children[i];
				q.push(tree[cr].children[i]);
			}
			else
			{
				tree[cr].children[i]=tree[tree[cr].fail].children[i];
			}
		}
		int tmp=tree[cr].fail;
		while(tmp!=root&&!tree[tmp].exsit)
		{
			tmp=tree[tmp].fail;
		}
		if(tree[tmp].exsit)
		{
			tree[cr].exsit=true;
		}
	}
}
int Match(char s[])
{
	int now=root;
	int ans=0;
	int len=strlen(s);
	for(int i=0;i<len;i++)
	{
		int k=s[i]-'a';
		if(tree[now].children[k]!=-1)
		{
			now=tree[now].children[k];
		}
		else
		{
			int p=tree[now].fail;
			while(p!=-1&&tree[p].children[k]==-1)
			{
				p=tree[p].fail;
			}
			if(p==-1)
			{
				now=root;
			}
			else
			{
				now=tree[p].children[k];
			}
		}
		if(tree[now].exsit)
		{
			int tn=now;
			while(tn!=-1&&tn!=root&&!vis[tn])
			{
				if(tree[tn].exsit)
				{
					ans+=tree[tn].count;
				}
				//tree[tn].count=0;
				vis[tn]=true;
				tn=tree[tn].fail;
			}
		}
	}
	return ans;
}
int main()
{
	
	return 0;
}
