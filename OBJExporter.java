/*
 * Ну вы же понимаете, что код здесь только мой?
 * Well, you do understand that the code here is only mine?
 */

import java.util.List;

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
            str += "v " + vertices[i * 3 + 0] + " " + vertices[i * 3 + 1] + " " + (-vertices[i * 3 + 2]) + "\n";
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
    
     public static class OBJMesh {

        public final String name;
        public final int[] indices;
        public final float[] vertices;
        public final float[] normals;
        public final float[] textureCoords;

        public OBJMesh(String name, int[] indices, float[] vertices) {
            this(name, indices, vertices, null, null);
        }

        public OBJMesh(String name, int[] indices, float[] vertices, float[] normals, float[] textureCoords) {
            this.name = name;
            this.indices = indices;
            this.vertices = vertices;
            this.normals = normals;
            this.textureCoords = textureCoords;
        }

    }

    public static String writeMultiple(List<OBJMesh> meshes) {
        StringBuilder str = new StringBuilder();
        int vertexCount = 1;
        int uvCount = 1;

        for (OBJMesh objMesh : meshes) {
            str.append("o ").append(objMesh.name).append("\n");

            // Vertices
            str.append("# VERTICES \n");
            for (int i = 0; i < objMesh.vertices.length; i += 3) {
                str.append("v ").append(objMesh.vertices[i]).append(" ")
                        .append(objMesh.vertices[i + 1]).append(" ")
                        .append(objMesh.vertices[i + 2]).append("\n");
            }

            if (objMesh.normals != null) {
                // Normals
                str.append("# NORMALS \n");
                for (int i = 0; i < objMesh.normals.length; i += 3) {
                    str.append("vn ").append(objMesh.normals[i]).append(" ")
                            .append(objMesh.normals[i + 1]).append(" ")
                            .append(objMesh.normals[i + 2]).append("\n");
                }
            }

            if (objMesh.textureCoords != null) {
                // Texture coords
                str.append("# UV \n");
                for (int i = 0; i < objMesh.textureCoords.length; i += 2) {
                    str.append("vt ").append(objMesh.textureCoords[i]).append(" ")
                            .append(1.0f - objMesh.textureCoords[i + 1]).append("\n");
                }
            }

            // Indices
            str.append("# FACES \n");
            for (int i = 0; i < objMesh.indices.length; i += 3) {
                int v1 = objMesh.indices[i] + vertexCount;
                int uv1 = objMesh.indices[i] + uvCount;
                int v2 = objMesh.indices[i + 1] + vertexCount;
                int uv2 = objMesh.indices[i + 1] + uvCount;
                int v3 = objMesh.indices[i + 2] + vertexCount;
                int uv3 = objMesh.indices[i + 2] + uvCount;

                String face = "f " + v1 + "/" + uv1 + " " + v2 + "/" + uv2 + " " + v3 + "/" + uv3;
                str.append(face).append("\n");
            }
            str.append("\n");

            vertexCount += objMesh.vertices.length / 3;
            uvCount += objMesh.textureCoords.length / 2;
        }

        return str.toString();
    }

}
