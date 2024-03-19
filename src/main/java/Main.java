public class Main {
    
    public static void main(String[] args) {
        MapGenerator gen = new MapGenerator(1337);
        gen.gridGenerator(50,50);
        gen.generateRooms(5,5,10);
        gen.addTile('w',"Wall",false);
        gen.addTile('f',"Floor",false);


        gen.generateCorridors();
        gen.generateSpawns(0);
        gen.printMap();
        System.out.println(gen);
    }
}
