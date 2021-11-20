package com.blog.blog.controller;

import com.blog.blog.dto.PostBasicDTO;
import com.blog.blog.dto.PostDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostControllerTest {

    @Autowired
    PostController postController;

    PostDTO postDTO;

    @Before
    public void setup() {
        postDTO = new PostDTO();
        postDTO.setTitle("¿Qué es un blog y para qué sirve?");
        postDTO.setContent("Un blog es una página web en la que se publican regularmente artículos cortos con contenido actualizado y novedoso sobre temas específicos o libres.");
        postDTO.setImage("/usr/tmp/img1.jpg");
        postDTO.setCategory("Inicio");
        postDTO.setCreationDate("2021-11-18");
        postDTO.setCreatorUserId(1L);
    }

    @Test
    public void save() {
        ResponseEntity<PostDTO> httpResponse = postController.save(postDTO);
        Assert.assertEquals(httpResponse.getStatusCode(), HttpStatus.CREATED);
        Assert.assertEquals(postDTO.getTitle(), httpResponse.getBody().getTitle());
    }

    @Test
    public void getPostById() {
        Long id = 4L;
        ResponseEntity<PostDTO> httpResponse = postController.getPostById(id);
        Assert.assertEquals(httpResponse.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(id, httpResponse.getBody().getId());
    }

    @Test
    public void getDetailsByFilters() {
        ResponseEntity<List<PostBasicDTO>> httpResponse = postController.getDetailsByFilters(null, "Inicio");
        Assert.assertEquals(httpResponse.getStatusCode(), HttpStatus.OK);
        Assert.assertFalse(httpResponse.getBody().isEmpty());
    }

    @Test
    public void update(){
        Long id = 4L;
        String imagen = "/usr/tmp/img2.jpg";

        PostDTO postDTO2;
        postDTO2 = new PostDTO();
        postDTO2.setTitle("Este va a ser otro blog");
        postDTO2.setContent("Otro ejemplo de Blog");
        postDTO2.setImage("/usr/tmp/img2.jpg");
        postDTO2.setCategory("Inicio");
        postDTO2.setCreationDate("2021-11-18");
        postDTO2.setCreatorUserId(1L);
        postDTO2.setImage("/usr/tmp/img1.jpg");

        ResponseEntity<PostDTO> httpResponse = postController.update(id, postDTO2);
        Assert.assertEquals(httpResponse.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(postDTO2.getImage(), httpResponse.getBody().getImage());
    }

    @Test
    public void delete(){
        Long id = 5L;
        ResponseEntity<Void> httpResponse = postController.delete(id);
        Assert.assertEquals(httpResponse.getStatusCode(), HttpStatus.NO_CONTENT);
    }
}
