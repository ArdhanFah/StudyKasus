import java.util.Arrays;
import java.util.Scanner;

public class StudyKasusfix {
    static int maxMhs = 100;                            // Untuk Max Mahasiswa (Baris dari tabel)
    static String kolom[] = {"NIM", "Nama", "Kode MK", "Nama Mata Kuliah", "SKS"};  
    static String[][] dataMhs = new String[maxMhs][2];      // Array dimensi 1 berisi Max Mahasiswa (Baris) array dimensi 2 untuk NIM dan Nama
    static String dataKrs[][] = new String[maxMhs][2];      // Array dimensi 1 berisi Max Mahasiswa (Baris) array dimensi 2 untuk KodeMK dan Nama Mata Kuliah 
    static int totalSKS[] = new int[maxMhs];                // Array untuk menyimpan jumlah Total SKS per Mahasiswa
    static int sksMhs[] = new int[maxMhs];                  // Array untuk menyimpan jumkah sks per matkul 
    static int mahasiswa = 0;        
    static int indeksKrs = 0;                               // Agar tidak saling menimpa saat input tambahData 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String searchMahasiswa = "";
        
        pilihMenu(sc, searchMahasiswa);
 
    }

    public static void pilihMenu(Scanner sc, String searchMahasiswa){
        while (true) {
            int pilihan = 0;    // Untuk menyimpan pilihan pengguna dan mereset pilihan saat pilihan menu dijalankan

            System.out.print("\n=== Sistem Pemantauan KRS Mahasiswa ===");
            System.out.print("\n1. Tambah Data KRS\n2. Tampilkan Daftar KRS Mahasiswa\n3. Analisis Data KRS\n4. Keluar\nPilih Menu: ");
            pilihan = sc.nextInt();
            
            switch (pilihan) {
                case 1:
                    TambahData(sc);
                    break;
                case 2:
                    TampilData(sc, searchMahasiswa);
                    break;
                case 3:
                    AnalisisData();
                    break;
                case 4:
                System.out.println("Terima kasih!");
                    return;
                default:
                    System.out.println("Pilihan anda tidak valid! masukkan [1-4]");
                    continue;
            }
        }
    }

    public static void TambahData(Scanner sc){
        System.out.println("--- Tambah Data KRS ---");
        while(true) {
            int sksTotal = 0;
            int sks = 0;
            sc.nextLine(); 
            System.out.print("Nama Mahasiswa: ");
            dataMhs[mahasiswa][1] = sc.nextLine(); 
            System.out.print("NIM: ");
            dataMhs[mahasiswa][0] = sc.nextLine();
            while (true) {
                System.out.print("Kode Mata Kuliah: ");
                dataKrs[indeksKrs][0] = sc.nextLine();
                
                System.out.print("Nama Mata Kuliah: ");
                dataKrs[indeksKrs][1] = sc.nextLine();

                System.out.print("Jumlah SKS (1-3): ");
                sks = sc.nextInt();
                // System.out.println(Arrays.deepToString(dataKrs));
                if(sks < 1 || sks > 3){
                    System.out.println("SKS yang anda masukkan tidak valid! masukkan (1-3)");
                    continue;  
                } else {
                    sksMhs[mahasiswa] = sks;     // ini masuk tabel
                    sksTotal += sks;      // Menampilkan total SKS yang diinputkan per mahasiswa dan reser saat perulangan dimulai
                    totalSKS[mahasiswa] = sksTotal; // ini berfungsi untuk function tampil data nantinya
                }
                
                sc.nextLine();
                System.out.print("Tambah mata kuliah lain? (y/t): ");
                String tambah = sc.nextLine();
                if (tambah.equalsIgnoreCase("t")) {
                    System.out.println("Total SKS yang diambil: " + totalSKS[mahasiswa]);
                    mahasiswa++;
                    break;
                }

                indeksKrs++; 
                if(sksTotal >= 24){
                    System.out.println("Total SKS yang diambil: " + totalSKS[mahasiswa]);
                    break;
                }
            }  
            break;
        }
    }

    public static void TampilData(Scanner sc, String searchMahasiswa){
        System.out.println("--- Tampil Daftar KRS Mahasiswa ---");
        sc.nextLine();
        System.out.print("Masukkan NIM Mahasiswa: ");
        searchMahasiswa = sc.nextLine();

        System.out.println("\nDaftar KRS: ");
        for(int i = 0; i < dataKrs.length; i++){
            int tempat = 0;
            int index = -1;
            if(searchMahasiswa.equalsIgnoreCase(dataMhs[i][0])){
                index = i;
                for(String koloms : kolom){
                    System.out.print("\t" + koloms + "\t");
                }
                System.out.println();
                 
                for(int j = 0; j < dataKrs[i].length; j++){
                    if (dataKrs[i][j] != null) {
                        System.out.print("\t" + dataMhs[index][0] + "\t\t" + dataMhs[index][1]);
                        System.out.print("\t\t" + dataKrs[tempat][j]);
                    }
                    System.out.print("\t\t\t\t" + sksMhs[tempat]);
                    System.out.println();
                    tempat++;
                }
                
                System.out.println();
                System.out.print("Total SKS: " + totalSKS[index]);
            } 
        }   
    }

    public static void AnalisisData(){
        System.out.println("--- Analisis Data KRS ---");
        int count = 0;                                          // Deklarasi count
        for(int i = 0; i < maxMhs; i++){
            if(totalSKS[i] <= 20 && totalSKS[i] != 0){
                count++;                                        // Jika totalSKS[i] < 20 maka akan menambahkan value count + 1
            }
        }

        System.out.println("Jumlah Mahasiswa yang mengambil SKS kurang dari 20: " + count);
    }
}