package main

import "fmt"

func main() {
	// fmt.Println(minDistance("horse", "ros"))
	// fmt.Println(minDistance("intention", "execution"))
	fmt.Println(minDistance("plasma", "altruism"))
	fmt.Println(minDistance("", "a"))
}

func minDistance(word1 string, word2 string) int {
	na := len(word1)
	nb := len(word2)
	m := make([][]int, na+1)
	for i := range m {
		m[i] = make([]int, nb+1)
	}
	for i := 0; i <= na; i++ {
		m[i][0] = i
	}
	for i := 0; i <= nb; i++ {
		m[0][i] = i
	}
	for i := 1; i <= na; i++ {
		for j := 1; j <= nb; j++ {
			x := m[i-1][j]
			y := m[i][j-1]
			z := m[i-1][j-1]

			if word1[i-1] == word2[j-1] {
				m[i][j] = min(x+1, y+1, z)
			} else {
				m[i][j] = min(x+1, y+1, z+1)
			}
		}
	}
	return m[na][nb]
}

func min(a, b, c int) int {
	r := a
	if b < r {
		r = b
	}
	if c < r {
		r = c
	}
	return r
}
