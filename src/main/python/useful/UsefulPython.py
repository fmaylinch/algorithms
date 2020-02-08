
# Useful things to use when resolving algorithms.
# Type hints are optional.

from collections import OrderedDict, defaultdict, deque
import heapq
# from sortedcontainers import SortedList # https://pypi.org/project/sortedcontainers/
from typing import Dict, List, Set, Tuple


def lists():

    # Creation
    list1: List[int] = []  # or list()
    listFixed: Tuple[int] = (10, 11, 12)  # or tuple(...)
    list2: List[int] = [10, 11, 12]  # or list(...)

    # Basic usage
    list2.append(13)  # with sets you use: set1.add(x)
    x2 = list2[0]
    length = len(list2)

    # Initialise
    listZeros = [0] * x2  # [0, 0, ..., 0] `x` times
    listInc = [i for i in range(x2)]  # [0, 1, 2, 3, ..., x-1]

    # filter, map
    list2a = [f"x = {x}" for x in list2 if x % 2 == 0]  # filter and map with comprehensions
    list2b = map(lambda x: f"x = {x}", filter(lambda x: x % 2 == 0, list2))  # prefer comprehensions

    # Loops
    for x in list1:
        print(x)
    for i in range(len(list1)):
        print(i, list1[i])
    for i, x in enumerate(list1):
        print(i, x)


def stack_queue_heap():

    stack = deque()
    # stack = [] # deque() is faster
    stack.append("one")
    stack.append("two")
    stack.append("three")
    assert stack.pop() == "three"
    assert stack.pop() == "two"
    assert stack.pop() == "one"

    queue = deque()
    # queue = [] # deque() is faster
    queue.append("one")  # or appendLeft(x) and then pop()
    queue.append("two")
    queue.append("three")
    assert queue.popleft() == "one"
    assert queue.popleft() == "two"
    assert queue.popleft() == "three"

    heap = []
    heapq.heapify(heap)  # if heap already contains numbers
    heapq.heappush(heap, 3)
    heapq.heappush(heap, 1)
    heapq.heappush(heap, 2)
    assert heapq.heappop(heap) == 1
    assert heapq.heappop(heap) == 2
    assert heapq.heappop(heap) == 3

    # Using my convenience class

    min_heap = Heap()
    min_heap.push(3)
    min_heap.push(1)
    min_heap.push(2)
    assert min_heap.pop() == 1
    assert min_heap.pop() == 2
    assert min_heap.pop() == 3

    max_heap = HeapBy(key=lambda x: -x)
    max_heap.push(3)
    max_heap.push(1)
    max_heap.push(2)
    assert max_heap.pop() == 3
    assert max_heap.pop() == 2
    assert max_heap.pop() == 1

    # non-comparable class
    class Node:
        def __init__(self, value):
            self.value = value

    max_heap = HeapBy(key=lambda x: -x.value)
    max_heap.push(Node(3))
    max_heap.push(Node(1))
    max_heap.push(Node(2))
    max_heap.push(Node(1))
    assert max_heap.pop().value == 3
    assert max_heap.pop().value == 2
    assert max_heap.pop().value == 1
    assert max_heap.pop().value == 1


def maps():

    map1: Dict[str, int] = {}
    mapOrdered: Dict[str, int] = OrderedDict()
    set1: Set[str] = set()
    # setSorted1 = SortedDict()
    map2: Dict[str, int] = {"one": 1, "two": 2}

    x3 = map2["two"]
    map2["three"] = 3
    length = len(map2)


    mapDefaultInt: Dict[str, int] = defaultdict(int)
    assert mapDefaultInt["a"] == 0  # returns default value, creates entry
    assert len(mapDefaultInt) == 1  # length is 1 because entry was created

    mapDefaultList: Dict[str, List[int]] = defaultdict(list)
    mapDefaultList["a"].append(1)
    mapDefaultList["a"].append(2)
    assert mapDefaultList["a"] == [1, 2]

    for k in map1:  # mapInt.keys()
        print(k, map1[k])
    for v in map1.values():
        print(v)
    for k, v in map1.items():
        print(k, v)


def classes():

    b = BoxPython(1)
    b.add(2)
    b.add_box(BoxPython(3))
    assert b.value == 6
    b.value = 1
    assert b.value == 1


class BoxPython:

    def __init__(self, value):
        self.value = value  # uses setter

    @property
    def value(self):
        return self.__value  # __ for private

    @value.setter
    def value(self, value):
        self.__value = value

    def add(self, value):
        self.value += value

    def add_box(self, other: 'BoxPython'):
        self.value += other.value


class Heap:
    """
    Convenience class for simplifying heapq usage
    """

    def __init__(self, array=None, heapify=True):
        if array:
            self.heap = array
            if heapify:
                heapq.heapify(self.heap)
        else:
            self.heap = []

    def push(self, x):
        heapq.heappush(self.heap, x)

    def pop(self):
        return heapq.heappop(self.heap)


class HeapBy(Heap):
    """
    Heap where you can specify a key function for sorting
    """

    # heapq will use __lt__, so there we use the key function to order elements
    class Item:
        def __init__(self, value, key):
            self.key = key
            self.value = value
        def __lt__(self, other):
            return self.key(self.value) < other.key(other.value)

    def __init__(self, key, array=None, heapify=True):
        super().__init__(array, heapify)
        self.key = key

    def push(self, x):
        super().push(self.Item(x, self.key))

    def pop(self):
        return super().pop().value


lists()
stack_queue_heap()
maps()
classes()

