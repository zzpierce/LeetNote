package main

import "fmt"

func main() {
	n := [][]int{
		{1, 4, 3, 1, 3, 2},
		{3, 2, 1, 3, 2, 4},
		{2, 3, 3, 2, 3, 1},
	}
	fmt.Println(trapRainWater(n))
}

func trapRainWater(heightMap [][]int) int {

	heap := MinHeap{}
	n := len(heightMap)
	m := len(heightMap[0])
	check := make([][]bool, n)
	for i := 0; i < n; i++ {
		check[i] = make([]bool, m)
	}
	for i := 0; i < n; i++ {
		heap.Push(&P{x: i, y: 0, h: heightMap[i][0]})
		check[i][0] = true
		if m > 1 {
			heap.Push(&P{x: i, y: m - 1, h: heightMap[i][m-1]})
			check[i][m-1] = true
		}
	}
	for i := 1; i < m-1; i++ {
		heap.Push(&P{x: 0, y: i, h: heightMap[0][i]})
		check[0][i] = true
		if n > 1 {
			heap.Push(&P{x: n - 1, y: i, h: heightMap[n-1][i]})
			check[n-1][i] = true
		}
	}
	ret := 0
	for heap.Len() > 0 {
		p := heap.Pop()
		to := [][]int{{-1, 0}, {0, -1}, {1, 0}, {0, 1}}
		for i := 0; i < 4; i++ {
			x1 := p.x + to[i][0]
			y1 := p.y + to[i][1]
			if ce(check, x1, y1, n, m) {
				h := heightMap[x1][y1]
				if h < p.h {
					ret += p.h - h
					h = p.h
				}
				heap.Push(&P{x: x1, y: y1, h: h})
				check[x1][y1] = true
			}
		}
	}
	return ret
}

func ce(check [][]bool, i, j, n, m int) bool {
	if i < 0 || i >= n || j < 0 || j >= m {
		return false
	}
	return !check[i][j]
}

type P struct {
	h int
	x int
	y int
}

type MinHeap struct {
	n []*P
}

func (m *MinHeap) Len() int { return len(m.n) }

func (m *MinHeap) Pop() *P {
	if m.Len() == 0 {
		return nil
	}
	r := m.n[0]
	m.n[0] = m.n[m.Len()-1]
	m.n = m.n[:m.Len()-1]
	m.down(0)
	return r
}

func (m *MinHeap) Push(v *P) {
	m.n = append(m.n, v)
	m.up(m.Len() - 1)
}

func (m *MinHeap) down(i int) {
	x, y := i*2+1, i*2+2
	l := len(m.n)
	if x >= l && y >= l {
		return
	}

	if y >= l {
		if m.n[x].h < m.n[i].h {
			t := m.n[i]
			m.n[i] = m.n[x]
			m.n[x] = t
			m.down(x)
		}
	} else {
		if m.n[x].h <= m.n[y].h && m.n[x].h < m.n[i].h {
			t := m.n[i]
			m.n[i] = m.n[x]
			m.n[x] = t
			m.down(x)
		}
		if m.n[x].h > m.n[y].h && m.n[y].h < m.n[i].h {
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
	if m.n[x].h > m.n[i].h {
		t := m.n[i]
		m.n[i] = m.n[x]
		m.n[x] = t
		m.up(x)
	}
}
