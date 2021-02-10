/*
 * Ну вы же понимаете, что код здесь только мой?
 * Well, you do understand that the code here is only mine?
 */
 
/**
 *
 * @author MrJavaCoder
 */
public class OBJExporter {

    public static String export(int[] indices, float[] vertices, float[] textureCoords, float[] normals) {
        String str = "";

        // Vertex
        str += "# VERTICES \n";
        for (int i = 0; i < vertices.length / 3; i++) {
            str += "v " + vertices[i * 3 + 0] + " " + vertices[i * 3 + 1] + " " + (vertices[i * 3 + 2]) + "\n";
        }

        str += "# NORMALS \n";

        if (normals.length > 0) {
            // Normal
            for (int i = 0; i < normals.length / 3; i++) {
                str += "vn " + (-normals[i * 3 + 0]) + " " + (-normals[i * 3 + 1]) + " " + (normals[i * 3 + 2]) + "\n";
            }
        }
        
        str += "# UV \n";

        if (textureCoords.length > 0) {
            // UV
            for (int i = 0; i < textureCoords.length / 2; i++) {
                str += "vt " + textureCoords[i * 2 + 0] + " " + textureCoords[i * 2 + 1] + "\n";
            }
        }

        str += "# FACES \n";
        
        // Indices
        for (int i = 0, c = indices.length; i < c; i += 3) {
            int v1 = indices[i] + 1;
            int v2 = indices[i + 1] + 1;
            int v3 = indices[i + 2] + 1;

            String face = "f " + (v1 + "/" + v1 + "/" + v1) + " " + (v2 + "/" + v2 + "/" + v2) + " " + (v3 + "/" + v3 + "/" + v3);

            str += face + "\n";
        }

        return str;
    }

}
