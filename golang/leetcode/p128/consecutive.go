package main

import "fmt"

func main() {
	fmt.Println(longestConsecutive([]int{0, 3, 7, 2, 5, 8, 4, 6, 0, 1}))
}

func longestConsecutive(nums []int) int {
	m := map[int]*Node{}
	ret := 0
	for _, v := range nums {
		if m[v] != nil {
			continue
		}
		nl := m[v-1]
		nr := m[v+1]
		if nl == nil && nr == nil {
			m[v] = &Node{
				Left: 1, Right: 1,
			}
			ret = max(ret, 1)
		} else if nl == nil {
			m[v] = &Node{
				Left: 1, Right: 1 + nr.Right,
			}
			m[v+nr.Right].Left++
			ret = max(ret, 1+nr.Right)
		} else if nr == nil {
			m[v] = &Node{
				Left: 1 + nl.Left, Right: 1,
			}
			m[v-nl.Left].Right++
			ret = max(ret, 1+nl.Left)
		} else {
			m[v] = &Node{
				Left: nl.Left + 1, Right: nr.Right + 1,
			}
			m[v-nl.Left].Right += nr.Right + 1
			m[v+nr.Right].Left += nl.Left + 1
			ret = max(ret, 1+nl.Left+nr.Right)
		}
	}
	return ret
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

type Node struct {
	Left  int
	Right int
}
