#include<iostream>
#include<stdlib.h>
#include<vector>

using namespace std;

int binPow(int a, int b, int m) {
    int p = 1L;
    int e = a;
    while (b > 0) {
        if ((b & 1) == 1) p = (p * e) % m;
        e = (e * e) % m;
        b >>= 1;
    }
    return p;
}

const int N = 1e7 + 10;
int lp[N + 1];
vector<int> pr;

void getPrime() {
    for (int i = 2; i * i <= N; i++) {
        if (lp[i]) {
            for (int j = i * i; j <= N; j += i)
                lp[j] = false;
        }
    }
    for (int i = 2; i <= N; i ++) {
        if (lp[i]) pr.push_back(i);
    }
}

bool isPrimeBaisc(int x) {
    for (int i = 2; i * i <= x; i ++) {
        if (x % i == 0) return false;
        return true;
    }
}

bool isPrimeFermat(int x, int iter = 5) {
    if (x < 4) return x == 2 || x == 3;
    for (int i = 0; i < iter; i ++) {
        int a = 2 + rand() % (x - 3);
        if (binPow(a, x - 1, x) != 1) return false;
    }
    return true;
}