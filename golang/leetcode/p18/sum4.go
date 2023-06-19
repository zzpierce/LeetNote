package main

import (
	"fmt"
	"sort"
)

func main() {
	fmt.Println(fourSum([]int{1, 0, -1, 0, -2, 2}, 0))
	fmt.Println(fourSum([]int{}, 0))
	fmt.Println(fourSum([]int{-2, -1, -1, 1, 1, 2, 2}, 0))
}

func fourSum(nums []int, target int) [][]int {
	sort.Slice(nums, func(i, j int) bool {
		return nums[i] < nums[j]
	})
	m := map[int][]int{}
	ce := map[string]bool{}
	for i, v := range nums {
		if m[v] == nil {
			m[v] = []int{i}
		} else {
			m[v] = append(m[v], i)
		}
	}
	l := len(nums)
	ret := [][]int{}
	for i := 0; i < l; i++ {
		for j := i + 1; j < l; j++ {
			for k := j + 1; k < l; k++ {
				t := m[target-nums[i]-nums[j]-nums[k]]
				for _, y := range t {
					if y != i && y != j && y != k && y > k {
						key := fmt.Sprintf("%d_%d_%d", nums[i], nums[j], nums[k])
						if !ce[key] {
							ret = append(ret, []int{nums[i], nums[j], nums[k], nums[y]})
							ce[key] = true
						}
					}
				}
			}
		}
	}
	return ret
}
