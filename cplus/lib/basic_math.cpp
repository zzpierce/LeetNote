
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

int gcd (int a, int b) {
    if (b == 0)
        return a;
    else
        return gcd (b, a % b);
}