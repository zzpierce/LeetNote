package main

import "fmt"

func main() {
	// fmt.Println(canCross([]int{0, 1, 3, 5, 6, 8, 12, 17}))
	fmt.Println(canCross([]int{0, 1, 2, 3, 4, 8, 9, 11}))
}

func canCross(stones []int) bool {
	m := map[int]map[int]bool{}
	id := map[int]int{}
	for i, s := range stones {
		id[s] = i
	}
	m[0] = make(map[int]bool)
	m[0][0] = true
	l := len(stones)
	for i := 0; i < l; i++ {
		if i == l-1 && len(m[i]) > 0 {
			return true
		}
		vi := stones[i]
		for s := range m[i] {
			if s-1 > 0 && id[vi+s-1] != 0 {
				if m[id[vi+s-1]] == nil {
					m[id[vi+s-1]] = make(map[int]bool)
				}
				m[id[vi+s-1]][s-1] = true
			}
			if s > 0 && id[vi+s] != 0 {
				if m[id[vi+s]] == nil {
					m[id[vi+s]] = make(map[int]bool)
				}
				m[id[vi+s]][s] = true
			}
			if s+1 > 0 && id[vi+s+1] != 0 {
				if m[id[vi+s+1]] == nil {
					m[id[vi+s+1]] = make(map[int]bool)
				}
				m[id[vi+s+1]][s+1] = true
			}
		}
	}
	return false
}
