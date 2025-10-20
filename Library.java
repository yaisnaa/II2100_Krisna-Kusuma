import java.util.ArrayList;
import java.util.List;

public class Library {
    // Todo: Buat atribut name (String) dan sections (List<Section>)
    private String name;
    private List<Section> sections;

    public Library(String name) {
        // Todo: Implementasi konstruktor dengan inisialisasi atribut name dengan parameter name
        // dan inisialisasi sections sebagai ArrayList kosong
        this.name = name;
        this.sections = new ArrayList<>();
    }

    // Todo: Implementasi method addSection yang menerima parameter Section
    // Method ini menambahkan section ke list sections
    // Tampilkan pesan: "Section '[nama section]' added to [nama library]"
    public void addSection(Section section){
        sections.add(section);
        System.out.printf("Section '%s' added to %s%n", section.getSectionName(), name);
    }

    // Todo: Implementasi method findBook yang menerima parameter String title
    // Method ini mencari buku berdasarkan judul di semua section
    // Jika ditemukan, tampilkan:
    // - "Found "[title]" in [nama section] section"
    // - Detail buku menggunakan getDetails()
    // Jika tidak ditemukan, tampilkan: "Book "[title]" not found in library"
    public void findBook(String title){
        for(Section section : sections){
            for(Section.Book book : section.getBooks()){
                if(book.getTitle().equalsIgnoreCase(title)){
                    System.out.printf("Found \"%s\" in %s section%n", title, section.getSectionName());
                    System.out.println(book.getDetails());
                    return;
                }
            }
        }
        System.out.printf("Book \"%s\" not found in library%n", title);
    }
    // Todo: Implementasi method displayAllBooks tanpa parameter
    // Method ini menampilkan semua buku di semua section
    public void displayAllBooks(){
        for(Section section : sections){
            System.out.printf("Section: %s%n", section.getSectionName());
            section.displayBooks();
            System.out.println();
        }
    }

    // Todo: Implementasi getter getName() yang mengembalikan name
    public String getName(){
        return name;
    }

    // Todo: Implementasi getter getSections() yang mengembalikan sections
    public List<Section> getSections(){
        return sections;
    }
    // Static Nested Class - Section
    public static class Section {
        // Todo: Buat atribut sectionName (String) dan books (List<Book>)
        private String sectionName;
        private List<Book> books;

        public Section(String sectionName) {
            // Todo: Implementasi konstruktor
            // Inisialisasi sectionName dengan parameter
            // Inisialisasi books sebagai ArrayList kosong
            this.sectionName = sectionName;
            this.books = new ArrayList<>();
        }

        // Todo: Implementasi method addBook yang menerima parameter Book
        // Method ini menambahkan book ke list books
        // Tampilkan pesan: "Book "[judul buku]" added to [nama section] section"
        public void addBook(Book book) {
            books.add(book);
            System.out.printf("Book \"%s\" added to %s section%n", book.getTitle(), sectionName);
        }
        // Todo: Implementasi method removeBook yang menerima parameter String title
        // Method ini menghapus buku berdasarkan judul dari list books
        // Gunakan looping untuk mencari buku dengan judul yang sesuai (case-insensitive)
        // Jika ditemukan:
        // - Hapus buku dari list
        // - Tampilkan: "Book "[title]" removed from [nama section] section"
        // Jika tidak ditemukan:
        // - Tampilkan: "Book "[title]" not found in [nama section] section"
        public void removeBook(String title){
            Book toRemove = null;
            for (Book book : books) {
                if (book.getTitle().equalsIgnoreCase(title)) {
                    toRemove = book;
                    break;
                }
            }
            if (toRemove != null) {
                books.remove(toRemove); 
                System.out.printf("Book \"%s\" removed from section %s%n", title, sectionName);
            } else {
                System.out.printf("Book \"%s\" not found in section %s%n", title, sectionName);
            }
        }
        
        // Todo: Implementasi method displayBooks tanpa parameter
        // Jika books kosong, tampilkan: "No books in this section"
        // Jika ada buku, tampilkan detail setiap buku
        public void displayBooks(){
            if (books.isEmpty()){
                System.out.println("No books in this section");
                return;
            }
            for(Book book : books){
                System.out.println(book.getDetails());
            }
        }

        // Todo: Implementasi getter getSectionName() yang mengembalikan sectionName
        public String getSectionName(){
            return sectionName;
        }

        // Todo: Implementasi getter getBooks() yang mengembalikan books
        public List<Book> getBooks(){
            return books;
        }
        
        // Non-static Inner Class - Book
        public class Book {
            // Todo: Buat atribut:
            // - title (String)
            // - author (String)
            // - isAvailable (boolean)
            // - borrowedBy (String)
            private String title;
            private String author;
            private boolean isAvailable;
            private String borrowedBy;

            public Book(String title, String author) {
                // Todo: Implementasi konstruktor
                // Inisialisasi title dan author dengan parameter
                // Set isAvailable = true
                // Set borrowedBy = null
                this.title = title;
                this.author = author;
                this.isAvailable = true;
                this.borrowedBy = null;
            }

            // Todo: Implementasi method borrowBook yang menerima parameter String memberName
            // Jika buku sudah dipinjam (!isAvailable):
            // - Tampilkan: "Error: Book "[title]" is already borrowed by [borrowedBy]"
            public void borrowBook(String memberName){
                if(!isAvailable){
                    System.out.printf("Error: Book \"%s\" is already borrowed by %s%n", title, borrowedBy);
                }
                else{
                    isAvailable = false;
                    borrowedBy = memberName;
                    System.out.printf("Book \"%s\" is now borrowed by %s%n", title, memberName);
                }
            }
            
            // Jika tersedia:
            // - Set isAvailable = false
            // - Set borrowedBy = memberName
            // - Tampilkan: "Book "[title]" is now borrowed by [memberName]"

            // Todo: Implementasi method returnBook tanpa parameter
            // Jika buku sedang tidak dipinjam (isAvailable):
            // - Tampilkan: "Error: Book "[title]" is not currently borrowed"
            public void returnBook(){
                if (isAvailable){
                    System.out.printf("Error: Book \"%s\" is not currently borrowed%n", title);
                }
                else{
                    System.out.printf("Book \"%s\" returned by %s%n", title, borrowedBy);
                    isAvailable = true;
                    borrowedBy = null;
                }
            }
           
            // Jika sedang dipinjam:
            // - Tampilkan: "Book "[title]" returned by [borrowedBy]"
            // - Set isAvailable = true
            // - Set borrowedBy = null

            // Todo: Implementasi method getDetails() yang mengembalikan String
            // Buat variabel status:
            // - Jika isAvailable = true, status = "Available"
            // - Jika isAvailable = false, status = "Borrowed by [borrowedBy]"
            // Return format: "Book "[title]" by [author] [[status]] - Section: [sectionName]"
            // Note: Book dapat mengakses sectionName dari enclosing class Section
            public String getDetails(){
                String status = isAvailable ? "Available" : "Borrowed by " + borrowedBy;
                return String.format("Book \"%s\" by %s [%s] - Section: %s", title, author, status, sectionName);
            }
            // Todo: Implementasi getter getTitle() yang mengembalikan title
            public String  getTitle(){
                return title;
            }

            // Todo: Implementasi getter getAuthor() yang mengembalikan author
            public String getAuthor(){
                return author;
            }

            // Todo: Implementasi getter isAvailable() yang mengembalikan isAvailable
            public boolean isAvailable(){
                return isAvailable;
            }
        }
    }

    public static class Member {
        // Todo: Buat atribut:
        // - name (String)
        // - borrowedBooks (List<Section.Book>)
        // - maxBorrowLimit (int)
        private String name;
        private List<Section.Book> borrowedBooks;
        private int maxBorrowLimit;

        public Member(String name, int maxBorrowLimit) {
            // Todo: Implementasi konstruktor
            // Inisialisasi name dan maxBorrowLimit dengan parameter
            // Inisialisasi borrowedBooks sebagai ArrayList kosong
            this.name = name;
            this.maxBorrowLimit = maxBorrowLimit;
            this.borrowedBooks = new ArrayList<>();
        }

        // Todo: Implementasi method canBorrow() yang mengembalikan boolean
        // Return true jika jumlah borrowedBooks < maxBorrowLimit
        // Return false jika sudah mencapai limit
        public boolean canBorrow(){
            return borrowedBooks.size() < maxBorrowLimit;
        }
        // Todo: Implementasi method borrowBook yang menerima parameter Section.Book book
        // Jika member tidak bisa meminjam lagi (!canBorrow()):
        // - Tampilkan: "Error: [name] has reached the borrow limit of [maxBorrowLimit] books"
        public void borrowBook(Section.Book book){
            if(!canBorrow()){
                System.out.printf("Error: %s has reached the borrow limit of %d books%n", name, maxBorrowLimit);
                return;
            }
            if (!book.isAvailable()){
                System.out.printf("Error: Book \"%s\" is not available%n", book.getTitle());
                return;
            }
            book.borrowBook(name);
            borrowedBooks.add(book);
        }
        // Jika buku tidak tersedia (!book.isAvailable()):
        // - Tampilkan: "Error: Book "[judul buku]" is not available"
      
        // Jika bisa meminjam:
        // - Panggil book.borrowBook(name)
        // - Tambahkan book ke borrowedBooks

        // Todo: Implementasi method returnBook yang menerima parameter Section.Book book
        // Jika book ada di borrowedBooks:
        // - Panggil book.returnBook()
        // - Hapus book dari borrowedBooks
        // Jika book tidak ada di borrowedBooks:
        // - Tampilkan: "Error: [name] has not borrowed "[judul buku]""
        public void returnBook(Section.Book book){
            if(borrowedBooks.contains(book)){
                book.returnBook();
                borrowedBooks.remove(book);
            }
            else{
                System.out.printf("Error: %s has not borrowed \"%s\"%n", name, book.getTitle());
            }
        }

        // Todo: Implementasi method displayBorrowedBooks tanpa parameter
        // Tampilkan: "[name]'s Borrowed Books ([jumlah borrowed]/[maxBorrowLimit]):"
        // Jika borrowedBooks kosong:
        // - Tampilkan: "No books currently borrowed"
        // Jika ada buku yang dipinjam:
        // - Untuk setiap buku, tampilkan: "  - [title] by [author]"
        public void displayBorrowedBooks(){
            System.out.printf("%s's Borrowed Books (%d/%d):%n", name, borrowedBooks.size(), maxBorrowLimit);
            if(borrowedBooks.isEmpty()){
                System.out.println("No books currently borrowed");
            }
            else{
                for(Section.Book book : borrowedBooks){
                    System.out.printf(" - %s by %s%n", book.getTitle(), book.getAuthor());
                }
            }
        }
    }
}