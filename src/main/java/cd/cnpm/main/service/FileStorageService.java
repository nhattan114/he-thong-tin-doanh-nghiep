/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cd.cnpm.main.service;
import java.nio.file.Path;
import java.util.stream.Stream;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;


/* Dùng để xử lý upload file */
public interface FileStorageService {
  public void init();
  public void save(MultipartFile file, String fileName);
  public Resource load(String filename);
  public void delete(String filename);
  public void deleteAll();
  public Stream<Path> loadAll();
  public void setPath(String path);
  public String getPath();
}