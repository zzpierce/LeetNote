package main

import "fmt"

func main() {
	n := []int{1, 4, 3, 4, 1, 2, 1, 3, 1, 3, 2, 3, 3}
	//n := []int{1, 3, 2, 2, 3, 1}
	//n := []int{4, 5, 5, 6}
	wiggleSort(n)
	fmt.Println(n)
}

func wiggleSort(nums []int) {
	s := MedianSort(nums)
	x := 0
	y := s
	for x < y {
		if nums[x] != nums[s] {
			x++
		} else if nums[y] == nums[s] {
			y--
		} else {
			swap(nums, x, y)
		}
	}
	x = len(nums) - 1
	y = s
	for x > y {
		if nums[x] != nums[s] {
			x--
		} else if nums[y] == nums[s] {
			y++
		} else {
			swap(nums, x, y)
		}
	}
	ret := []int{}
	si := len(nums) - 1
	j := 0
	for i := si; i > si/2; i-- {
		ret = append(ret, nums[si/2-j])
		ret = append(ret, nums[i])
		j++
	}
	if len(ret) < len(nums) {
		ret = append(ret, nums[0])
	}
	copy(nums, ret)
}

// 中位数O(n)
func MedianSort(n []int) int {
	t := find(n, 0, len(n)-1)
	return t
}

func find(n []int, x, y int) int {
	if x == y {
		return x
	}
	swap(n, x, (x+y)/2)
	t := x
	s := x
	e := y
	for s < e {
		for n[e] >= n[t] && s < e {
			e--
		}
		if n[e] < n[t] && s < e {
			c := n[t]
			n[t] = n[e]
			n[e] = c
			s = t + 1
			t = e
		}
		for n[s] <= n[t] && s < e {
			s++
		}
		if n[s] > n[t] && s < e {
			c := n[t]
			n[t] = n[s]
			n[s] = c
			e = t - 1
			t = s
		}
	}
	if t > (len(n)-1)/2 {
		t = find(n, x, t-1)
	} else if t < (len(n)-1)/2 {
		t = find(n, t+1, y)
	}
	return t
}

func swap(n []int, x, y int) {
	c := n[x]
	n[x] = n[y]
	n[y] = c
}
