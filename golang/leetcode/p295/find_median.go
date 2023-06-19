package main

import "fmt"

func main() {
	n := []int{12, 10, 13, 11, 5, 15, 1, 11, 6, 17}
	obj := Constructor()
	for i := range n {
		obj.AddNum(n[i])
	}
	fmt.Println(obj.FindMedian())
}

type MedianFinder struct {
	data []int
}

/** initialize your data structure here. */
func Constructor() MedianFinder {
	return MedianFinder{}
}

func (this *MedianFinder) AddNum(num int) {
	this.data = append(this.data, num)
	MedianSort(this.data)
}

func (this *MedianFinder) FindMedian() float64 {
	l := len(this.data)
	if l%2 == 0 {
		r := this.data[(l-1)/2] + this.data[(l-1)/2+1]
		return float64(r) / 2
	} else {
		return float64(this.data[(l-1)/2])
	}
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * obj := Constructor();
 * obj.AddNum(num);
 * param_2 := obj.FindMedian();
 */

// 中位数O(n)
func MedianSort(n []int) {
	aim(n, 0, len(n)-1, (len(n)-1)/2)
	if len(n)%2 == 0 {
		aim(n, (len(n)+1)/2, len(n)-1, (len(n)-1)/2+1)
	}
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
