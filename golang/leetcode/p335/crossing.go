package main

import "fmt"

func main() {
	fmt.Println(isSelfCrossing([]int{2, 1, 1, 2}))
	fmt.Println(isSelfCrossing([]int{1, 2, 3, 4}))
}

func isSelfCrossing(distance []int) bool {
	ret := false
	small := false
	for i := 2; i < len(distance); i++ {
		if !small && distance[i] > distance[i-2] {
			continue
		}
		if !small && distance[i] <= distance[i-2] {
			small = true
			if i == 3 && distance[i] == distance[i-2] {
				distance[i-1] -= distance[i-3]
			}
			if i > 3 && distance[i] >= distance[i-2]-distance[i-4] {
				distance[i-1] -= distance[i-3]
			}
			continue
		}
		if small && distance[i] >= distance[i-2] {
			ret = true
			break
		}
	}
	return ret
}
