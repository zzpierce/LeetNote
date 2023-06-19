package main

import (
	"container/list"
	"fmt"
)

func main() {

	fmt.Println(ladderLength("hit", "cog", []string{"hot", "dot", "dog", "lot", "log", "fog"}))

}

func ladderLength(beginWord string, endWord string, wordList []string) int {

	dis := make([]int, len(wordList))
	check := make([]int, len(wordList))

	que := list.New()
	que.PushBack(Node{Len: 1, V: beginWord})
	for i, w := range wordList {
		if w == beginWord {
			check[i] = 1
		}
	}

	for que.Len() != 0 {
		s := que.Front().Value.(Node)
		que.Remove(que.Front())
		for i, w := range wordList {
			if check[i] == 0 && dif(s.V, w) == 1 {
				if w == endWord {
					return s.Len + 1
				}
				dis[i] = s.Len + 1
				check[i] = 1
				que.PushBack(Node{Len: s.Len + 1, V: w})
			}
		}
	}
	return 0
}

func dif(x, y string) int {
	r := 0
	for i := 0; i < len(x); i++ {
		if x[i] != y[i] {
			r++
		}
	}
	return r
}

type Node struct {
	Len int
	V   string
}
