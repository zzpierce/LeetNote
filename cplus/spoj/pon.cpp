#include<iostream>
#include<stdlib.h>
#include<stdio.h>
#include<vector>
#include<cstring>

using namespace std;

typedef unsigned long long ll;

const int maxn = 1e5 + 10;
vector<int> primes;
bool vis[maxn];

ll mul_mod(ll a, ll b, ll m){
    ll ans = 0, base = a;
    while(b){
        if(b & 1) ans = (ans + base) % m;
        base %= m;
        base = base * 2 % m;
        b >>= 1;
    }
    return ans;
}
ll pow_mod(ll a, ll b, ll m){
    ll ans = 1, base = a % m;
    while(b){
        if(b & 1) ans = mul_mod(ans, base, m);
        base %= m;
        base = mul_mod(base, base, m);
        b >>= 1;
    }
    return ans % m;
}
void dowork(){
    memset(vis, 0, sizeof(vis));
    for(int i = 2; i < maxn / 2; i++)
        for(int j = i * 2; j > 0 && j < maxn; j += i)
            vis[j] = 1;
    for(int i = 2; i < 1e5; i++)
        if(!vis[i]) primes.push_back(i);
}
bool check(ll x){
    if(x == 2) return true;
    if(x % 2 == 0) return false;
    ll q = x - 1;
    int k = 0;
    while(!(q & 1)){
        k++; q >>= 1;
    }
    bool flag = true;
    for(int i = 0; i < 100; i++){
        ll a = primes[i];
        if(a == x) return true;
        if(pow_mod(a, q, x) == 1)
            continue;
        bool none = true;
        for(int j = 0; j < k; j++){
            ll qi = 1 << j;
            ll tmp = pow_mod(a, qi * q, x);
            if(tmp == x - 1){
                none = false;
                break;
            }
            else if(tmp == 1)
                break;
        }
        if(none) {
            flag = false;
            break;
        }
    }
    return flag;
}
int main(){
    dowork();
    int t;
    scanf("%d", &t);
    while(t--){
        ll n;
        scanf("%lld", &n);
        if(check(n)) puts("YES");
        else puts("NO");
    }
}