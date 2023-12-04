import java.util.Scanner
import kotlin.system.exitProcess


val reader = Scanner(System.`in`)


var pilihanJenis = arrayOf("","Ikan","Kucing")
var MilihJenis = 2
var pilihanKelamin = arrayOf("","Jantan", "Betina")
var MakanArr = arrayOf("","Ayam", "Coklat", "Ikan")
var HungerIndex = 0
var hygineIndex = 0
var energyIndex = 0


var NamaPet  = "Oren"
var JenisPet = "Kucing"
var JKPet = "Betina"
var UmurPet = 1


interface DataPet{
    var Nama: String
    var jenisHewan: String
    var JK : String
    var Umur: Int
}


data class DataUser(val username: String, val password: String)


fun signUp(){
    println("Masukkan Username : ")
    val inputUser = readLine()!!
    println("Masukkan Password : ")
    val inputPassword = readLine()!!
    val user1 = DataUser(inputUser,inputPassword)
    // Nantinya data dari user ini akan disimpan didalam database
    println("Selamat datang !! ${inputUser}")
}


fun munculStats(){
    // Prevent stats outputing too many value
    while(HungerIndex > 10 || hygineIndex > 10 || energyIndex > 10){
        if (HungerIndex>10) {
            HungerIndex--
        } else if (hygineIndex>10){
            hygineIndex--
        } else if (energyIndex>10){
            energyIndex--
        }
    }
    // Prevent stats outputing minus value
    while(HungerIndex < 0 || hygineIndex < 0 || energyIndex < 0){
        if (HungerIndex<0) {
            HungerIndex++
        } else if (hygineIndex<0){
            hygineIndex++
        } else if (energyIndex<0){
            energyIndex++
        }
    }
    println("Hunger($HungerIndex) | Hygine($hygineIndex) | energy($energyIndex)")
}


open class Pet(override var Nama: String = "Oren",override var jenisHewan: String = "Kucing",
               override var JK : String = "Jantan", override var Umur: Int = 1) : DataPet{
    open fun regNama(){
        val iniNull: String? = null
        print("Tentukan nama pet kamu : ")
        var inputNama = readLine()
        try{
            if (inputNama.isNullOrBlank()) {
                inputNama = iniNull!!
            }
            NamaPet = inputNama
            Nama = inputNama
        } catch(e: NullPointerException) {
            val errMessage = "Inputan tidak boleh NULL!!!"
            println(errMessage)
            regNama()
        }
    }


    open fun regJenis(){
        println("Tentukan Jenis Hewan (1.Ikan 2.Kucing) : ")
        try {
            val inputJenis: Int = reader.nextInt()
            MilihJenis = inputJenis
            jenisHewan = pilihanJenis[inputJenis]
            JenisPet = pilihanJenis[inputJenis]
        } catch (e: ArrayIndexOutOfBoundsException) {
            val errMessage = "Pilihan Jenis Hewan Salah !!"
            println(errMessage)
            regJenis()
        }
    }


    fun regKelamin(){
        print("Tentukan Jenis Kelamin (1. Jantan 2. Betina) : ")
        try {
            val inputJK: Int = reader.nextInt()
            JK = pilihanKelamin[inputJK]
            JKPet = pilihanKelamin[inputJK]
        } catch (e: ArrayIndexOutOfBoundsException) {
            val errMessage = "Pilihan Jenis Kelamin Salah !!"
            println(errMessage)
            regKelamin()
        }
    }


    fun regUmur(){
        val iniNull: String? = null
        print("Berapa umur ${Nama} : ")
        var inputUmur = readLine()
        try{
            if (inputUmur.isNullOrBlank()) {
                inputUmur = iniNull!!
            }
            Umur = inputUmur.toInt()
            UmurPet = inputUmur.toInt()


        } catch(e: Exception) {
            val errMessage = "Inputan salah !!!"
            println(errMessage)
            regUmur()
        }
    }


    open fun mandi(){
        if(energyIndex != 0){
            println("${Nama} sedang mandi")
            hygineIndex += 2
            energyIndex -= 1
        }else if(energyIndex == 0){
            println("${Nama} terlalu capek untuk mandi ")
        }


    }


    open fun makan() {
        println("${Nama} sedang makan")
        HungerIndex += 1
        hygineIndex -= 1


    }
    open fun makan( pMakanArr : Int ){
        println("${Nama} sedang makan " + MakanArr[pMakanArr] )
        HungerIndex += 2
        hygineIndex -= 1
    }


    open fun tidur() {
        println("Ssstt!, ${Nama} sedang tidur")
        energyIndex += 2
        hygineIndex -= 2
    }


    open fun bermain(){
        if(energyIndex < 2 || HungerIndex < 1){
            println("${Nama} terlalu lemas, dia tidak bisa bermain!!!")
            return
        }
        println("${Nama} sedang bermain!!!")
        energyIndex -= 2
        hygineIndex -= 3
        HungerIndex -= 1
    }
}


class Kucing(pName : String, pJenisHewan : String, pJK : String, pUmur : Int = 1, var jumKaki : Int = 4) :
    Pet(pName, pJenisHewan, pJK, pUmur){
    override fun tidur() {
        println("$Nama sedang tidur di cat tower")
    }
}


class Ikan(pName : String, pJenisHewan : String, pJK : String, pUmur : Int = 1, var jumSirip : Int = 2) :
    Pet(pName, pJenisHewan, pJK, pUmur){
    override fun tidur() {
        println("$Nama sedang tidur di dalam karang")
    }
}


fun main() {
    println("Selamat datang di aplikasi MyPet")
    signUp()
    var pet = Pet()
    pet.regNama()
    pet.regJenis()
    pet.regKelamin()
    pet.regUmur()
    println("Halo aku adalah ${pet.Nama}, aku ${pet.jenisHewan} ${pet.JK}  berumur ${pet.Umur} tahun :D")
    pet = Pet(NamaPet, JenisPet, JKPet, UmurPet)
    val kucing = Kucing(pet.Nama, pet.jenisHewan, pet.JK, pet.Umur)
    val ikan = Ikan(pet.Nama, pet.jenisHewan, pet.JK, pet.Umur)
    while (true) {
        munculStats()
        println("Aktivitas apa yang ingin kamu lakukan?")
        println("1. Beri ${pet.Nama} makan")
        println("2. Beri ${pet.Nama} makanan spesial")
        println("3. Perintahkan ${pet.Nama} untuk mandi")
        println("4. Perintahkan ${pet.Nama} untuk tidur")
        println("5. Perintahkan ${pet.Nama} untuk bermain")
        println("6. Exit")
        println("Masukkan pilihan anda : ")
        val pilihan: Int = reader.nextInt()
        when (pilihan) {
            1 -> pet.makan()
            2 -> {
                println("Pilih Makanan Spesial untuk ${pet.Nama}!!!")
                fun kasihMakan() {
                    try {
                        var i = 1
                        while (i < MakanArr.size) {
                            println("${i}. ${MakanArr[i]} ")
                            i++
                        }
                        print("Masukkan pilihan anda : ")
                        val milih: Int = reader.nextInt()
                        pet.makan(milih)
                    } catch (e: ArrayIndexOutOfBoundsException) {
                        val errMessage = "Tidak ada Pilihan !!"
                        println(errMessage)
                        kasihMakan()
                    }
                }
                kasihMakan()
            }
            3 -> pet.mandi()
            4 -> {
                println("Pilih tempat tidur untuk ${pet.Nama}")
                println("1.Bantal")
                if(MilihJenis == 1){
                    println("2.Karang")
                    val inputPilih: Int = reader.nextInt()
                    if(inputPilih == 1)
                        pet.tidur()
                    else if(inputPilih == 2)
                        ikan.tidur()
                } else if (MilihJenis == 2){
                    println("2.Cat Tower")
                    val inputPilih: Int = reader.nextInt()
                    if(inputPilih == 1)
                        pet.tidur()
                    else if (inputPilih ==2)
                        kucing.tidur()
                }
                println("Bangunkan?")
                println("1. Bangunkan")
                println("2. Biarkan ${pet.Nama} beristirahat")
                val inputBangun: Int = reader.nextInt()
                if(inputBangun == 1)
                    continue
                else if (inputBangun ==2)
                    println("Selamat Tidur ${pet.Nama} !!!")
                exitProcess(-1)
            }
            5 -> pet.bermain()
            6 -> {
                println("Selamat Tinggal !!!")
                exitProcess(-1)
            }
        }
    }
}
