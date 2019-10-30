import java.util.*

var namaNode = arrayListOf<String>()
var dictNode = mutableMapOf<String, Int>()
var hasilAkhir = mutableMapOf<String, Int>()

var iterasi = 0
var stop = 0

fun jarakTotal(node: String): Int{
    var hasil = 0
    for (x in 0 until namaNode.size-1) {
        hasil = hasil + dictNode[(node[x].toString() + node[x+1].toString())]!!
    }
    return hasil
}

fun tukar(node: String, x: Int, y: Int): String {
    var newNode = ""
    val temp1 = node[x]
    val temp2 = node[y]
    for (n in node) {
        if(n == temp1) {
            newNode = newNode + temp2
        }
        else if(n == temp2) {
            newNode = newNode + temp1
        }
        else {
            newNode = newNode + n
        }
    }
    return newNode
}

fun hillClimb(node: String) {
    var test = ""
    var tnode = node
    var temp = jarakTotal(node)
    while (stop == 0) {
        iterasi = iterasi.plus(1)
        println("Iterasi-$iterasi")
        for (x in 0 until namaNode.size-1){
            for (y in x+1 until namaNode.size){
                test = tukar(node, x, y)
                println(test)
                if (jarakTotal(node) > jarakTotal(test)){
                    tnode = test
                    temp = jarakTotal(test)
                    hasilAkhir[tnode] = temp
                    println("$tnode ===> $temp")
                    break
                }
            }
            if (jarakTotal(node) > jarakTotal(test)){
                break
            }
        }

        if ((temp == jarakTotal(node)) and (tnode == node)){
            stop = 1
            print("Ending : $tnode ---> $temp")
        }
        hillClimb(tnode)
        break
    }
}
fun steepestHillClimb(node: String){
    var test = ""
    var tnode = node
    var temp = jarakTotal(node)
    while (stop == 0){
        iterasi = iterasi.plus(1)
        println("Iterasi-$iterasi")
        for (x in 0 until namaNode.size-1){
            for (y in x+1 until namaNode.size){
                test = tukar(node, x, y)
                println(test)
                if (temp > jarakTotal(test)) {
                    tnode = test
                    temp = jarakTotal(test)
                    hasilAkhir[tnode] = temp
                    println("$tnode ===> $temp")
                }
            }
        }
        //Jika node tidak ditemukan, maka node tsb menjadi solusion
        if ((temp == jarakTotal(node)) and (tnode == node)){
            stop = 1
            print("Ending : $tnode ---> $temp")
        }
        steepestHillClimb(tnode)
        break
    }
}

fun main(){

    println("==========Hill Climbing==========")
    val input = Scanner(System.`in`)
    print("Banyak Kota : ")
    val banyakArray = input.nextInt()

    for (i in 0 until banyakArray) {
        print("Nama Kota ${i + 1} : ")
        var kota = input.next()
        namaNode.add(i, kota)
    }

    for (i in 0 until namaNode.size){
        for (j in i+1 until namaNode.size){
            val kota1 = namaNode[i] + namaNode[j]
            val kota2 = namaNode[j] + namaNode[i]
            print("Jarak Kota $kota1 dan $kota2 : ")
            val jarak = input.nextInt()

            dictNode[kota1] = jarak
            dictNode[kota2] = jarak

        }
    }

    var awal = ""

    for (x in namaNode) awal += x

    println("Node Awal : $awal  ${jarakTotal(awal)}")
    print("1. Hill Climbing\n2. Steepest Hill Climbing\nPilih : ")
    val pilih = input.nextInt()
    if (pilih == 1) {
        hillClimb(awal)
    } else if (pilih == 2) {
        steepestHillClimb(awal)
    }
    println("\n" + hasilAkhir)
}