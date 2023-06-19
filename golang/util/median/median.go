package main

import "fmt"

func main() {
	n := []int{4, 4, 1, 11, 1, 1, 1, 1, 1, 1}
	MedianSort(n)
	fmt.Println(n)
}

// 找到排序后指定位置的数字
func MedianSort(n []int) {
	aim(n, 0, len(n)-1, (len(n)-1)/2)
}

func aim(n []int, x, y, d int) {
	if x == y {
		return
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
			t = e
		}
		for n[s] <= n[t] && s < e {
			s++
		}
		if n[s] > n[t] && s < e {
			c := n[t]
			n[t] = n[s]
			n[s] = c
			t = s
		}
	}
	if t > d {
		aim(n, x, t-1, d)
	} else if t < d {
		aim(n, t+1, y, d)
	}

}

func swap(n []int, x, y int) {
	c := n[x]
	n[x] = n[y]
	n[y] = c
}
