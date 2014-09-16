val l = List(1,2,3)
def sum(list: List[Int]): Int = list.foldLeft(0)((r,c) => r+c)

val ans = sum(l)