class Node:
    def __init__(self, val, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


def print_tree_levels(node):
    nodes = [node]
    children = []
    while nodes:
        values = []
        for n in nodes:
            if n:
                values.append(str(n.val))
                children.append(n.left)
                children.append(n.right)
            else:
                values.append('x')
        print(" ".join(values))
        nodes = children
        children = []


print_tree_levels(
    Node(1, Node(2, Node(4), Node(5)), Node(3, None, Node(6)))
)

