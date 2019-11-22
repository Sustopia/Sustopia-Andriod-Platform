#include<cstdio>
#include<cstring>
using namespace std;
const int maxn=(int)1<<20;
const int alphabet_size=26;
int tot,root;
struct trie_node
{
	int count,children[alphabet_size];
	bool exsit;
}tree[maxn];
int create_trie_node()
{
	tree[tot].count=0;
	tree[tot].exsit=false;
	memset(tree[tot].children,-1,sizeof(tree[tot].children));
	tot++;
	return tot-1;
}
void init()
{
	tot=0;
	root=create_trie_node();
}
void trie_insert(int root,char key[])
{
	int pos=0;
	while(key[pos]!='\0')
	{
		if(tree[root].children[key[pos]-'a']==-1)
		{
			tree[root].children[key[pos]-'a']=create_trie_node();
		}
		tree[root].count++;
		root=tree[root].children[key[pos]-'a'];
		pos++;
	}
	tree[root].exsit=true;
	tree[root].count++;
}
int trie_search(int root,char key[])
{
	int pos=0;
	while(key[pos]!='\0'&&root!=-1)
	{
		root=tree[root].children[key[pos]-'a'];
		pos++;
	}
	if(root==-1)
	{
		return 0;
	}
	else
	{
		return tree[root].count;
	}
}
int main()
{
	
	return 0;
}
