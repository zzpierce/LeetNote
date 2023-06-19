package main

import (
	"fmt"
	"strconv"
	"strings"
)

func main() {

	n := TreeNode{Val: 1}
	n.Left = &TreeNode{Val: 2}
	n.Right = &TreeNode{Val: 3}
	n.Right.Left = &TreeNode{Val: 4}
	n.Right.Right = &TreeNode{Val: 5}

	ser := Constructor()
	fmt.Println(ser.serialize(&n))

	t := ser.serialize(&n)
	m := ser.deserialize(t)
	fmt.Println(m)
}

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

type Codec struct {
}

func Constructor() Codec {
	return Codec{}
}

// Serializes a tree to a single string.
func (this *Codec) serialize(root *TreeNode) string {
	if root == nil {
		return ""
	}
	vs := []string{}
	que := []*TreeNode{}
	que = append(que, root)
	vs = append(vs, strconv.Itoa(root.Val))
	for len(que) > 0 {
		q := que[0]
		que = que[1:]
		if q.Left != nil {
			que = append(que, q.Left)
			vs = append(vs, strconv.Itoa(q.Left.Val))
		} else {
			vs = append(vs, "4399")
		}
		if q.Right != nil {
			que = append(que, q.Right)
			vs = append(vs, strconv.Itoa(q.Right.Val))
		} else {
			vs = append(vs, "4399")
		}
	}
	return strings.Join(vs, ",")
}

// Deserializes your encoded data to tree.
func (this *Codec) deserialize(data string) *TreeNode {
	if data == "" {
		return nil
	}
	vs := strings.Split(data, ",")

	root := &TreeNode{Val: toi(vs[0])}
	que := []*TreeNode{root}
	i := 1
	for len(que) > 0 {
		q := que[0]
		que = que[1:]
		if vs[i] != "4399" {
			q.Left = &TreeNode{Val: toi(vs[i])}
			que = append(que, q.Left)
		}
		i++
		if vs[i] != "4399" {
			q.Right = &TreeNode{Val: toi(vs[i])}
			que = append(que, q.Right)
		}
		i++
	}
	return root
}

func toi(v string) int {
	i, _ := strconv.Atoi(v)
	return i
}

/**
 * Your Codec object will be instantiated and called as such:
 * ser := Constructor();
 * deser := Constructor();
 * data := ser.serialize(root);
 * ans := deser.deserialize(data);
 */
