package main

import "fmt"

func main() {
	m := MinHeap{}
	m.Init([]int{1, 5, 4, 2, 7, 5, 2, 1})
	for m.Len() > 0 {
		fmt.Println(m.Pop())
	}
	m.Push(45)
	m.Push(33)
	m.Push(49)
	for m.Len() > 0 {
		fmt.Println(m.Pop())
	}
}

type MinHeap struct {
	n []int
}

func (m *MinHeap) Len() int { return len(m.n) }

func (m *MinHeap) Init(v []int) {
	m.n = make([]int, len(v))
	copy(m.n, v)
	for i := len(m.n) - 1; i >= 0; i-- {
		m.down(i)
	}
}

func (m *MinHeap) Pop() int {
	if m.Len() == 0 {
		return 0
	}
	r := m.n[0]
	m.n[0] = m.n[m.Len()-1]
	m.n = m.n[:m.Len()-1]
	m.down(0)
	return r
}

func (m *MinHeap) Push(i int) {
	m.n = append(m.n, i)
	m.up(m.Len() - 1)
}

func (m *MinHeap) down(i int) {
	x, y := i*2+1, i*2+2
	l := len(m.n)
	if x >= l && y >= l {
		return
	}

	if y >= l {
		if m.n[x] < m.n[i] {
			t := m.n[i]
			m.n[i] = m.n[x]
			m.n[x] = t
			m.down(x)
		}
	} else {
		if m.n[x] <= m.n[y] && m.n[x] < m.n[i] {
			t := m.n[i]
			m.n[i] = m.n[x]
			m.n[x] = t
			m.down(x)
		}
		if m.n[x] > m.n[y] && m.n[y] < m.n[i] {
			t := m.n[i]
			m.n[i] = m.n[y]
			m.n[y] = t
			m.down(y)
		}
	}
}

func (m *MinHeap) up(i int) {
	if i == 0 {
		return
	}
	x := (i - 1) / 2
	if m.n[x] > m.n[i] {
		t := m.n[i]
		m.n[i] = m.n[x]
		m.n[x] = t
		m.up(x)
	}
}
