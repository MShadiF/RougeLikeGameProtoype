public class Main {
    
    public static void main(String[] args) {
        MapGenerator gen = new MapGenerator(1337);
        gen.gridGenerator(50,50);
        gen.generateRooms(5,5,10);
        gen.addTile('f',"Floor",false);
        gen.addTile('w',"Wall",false);
        gen.addTile('G',"Goblin",false,1);
        gen.addTile('M',"Minotaur",false,5);
        gen.addTile('S',"Slime",false,2);

        gen.generateCorridors();
        gen.generateSpawns(20);
        gen.printMap();
        System.out.println(gen);
    }
}
