package main

import "fmt"

func longestPalindromeSubseq(s string) int {
	le := len(s)
	var m [10][10]int
	for i := 0; i < len(s); i++ {
		m[i][i] = 1
	}
	for gap := 1; gap < le; gap++ {
		for i := 0; i+gap < len(s); i++ {
			j := i + gap
			if gap > 1 {
				if s[i] == s[j] {
					m[i][j] = maxInt(m[i][j], m[i+1][j-1]+2)
					m[i][j] = maxInt(m[i][j], m[i+1][j])
					m[i][j] = maxInt(m[i][j], m[i][j-1])
				}
				if s[i] != s[j] {
					m[i][j] = maxInt(m[i][j], m[i+1][j])
					m[i][j] = maxInt(m[i][j], m[i][j-1])
				}
			}
			if gap == 1 {
				if s[i] == s[j] {
					m[i][j] = 2
				} else {
					m[i][j] = 1
				}
			}
		}
	}
	return m[0][le-1]
}

func maxInt(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func main() {
	fmt.Println(longestPalindromeSubseq("abcbac"))
	fmt.Println(longestPalindromeSubseq("bbbab"))
	fmt.Println(longestPalindromeSubseq("abcdef"))
}
