package hari.core.graphql.controllers;

import hari.core.graphql.helper.ResponseBody;
import hari.core.graphql.helper.GenerateData;
import hari.core.graphql.helper.Utils;
import hari.core.graphql.models.Lecturer;
import hari.core.graphql.service.LecturerService;
//import hari.core.graphql.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.UnknownHostException;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class LecturerController {

//    @Value("${server.local}")
//    String localServer;

    @Autowired
    LecturerService lecturerService;
    @Autowired
    Environment environment;

    protected Utils utils = new Utils();
    protected GenerateData generateData = new GenerateData();

    //
////    public String baseUrl() throws UnknownHostException {
////        if (env.equalsIgnoreCase("production")) {
////            return utils.baseUrl(environment);
////        } else {
////            return localServer;
////        }
////    }
//
//
    @QueryMapping
    public String getAllLecturer(@Argument String name) {
        return "Hello " + name;
    }
}
//
////    @GetMapping("/lecturer/{id}")
////    public ResponseEntity<ResponseBody> getById(@PathVariable("id") String id) {
////        try {
////            Optional<Lecturer> data = lecturerService.findById(id);
////
////            if (data.isPresent()) {
////                Lecturer _data = data.get();
////                String baseUrl = this.baseUrl();
////                String imageLink = baseUrl + "/uploads/media/lecturer/" + _data.getImage();
////                _data.setProfile(generateData.lecturer(_data.getUserId()));
////                _data.setImage(imageLink);
////
////                ResponseBody response = new ResponseBody(200, "Data Successfully Retrieved", _data);
////                return new ResponseEntity<>(response, HttpStatus.OK);
////            } else {
////                ResponseBody response = new ResponseBody(404, "Data Not Found", null);
////                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
////            }
////        } catch (Exception e) {
////            ResponseBody response = new ResponseBody(500, e.getMessage(), null);
////            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
////        }
////    }
////
////    @PostMapping("/lecturer")
////    public ResponseEntity<ResponseBody> create(
////            @RequestParam String slug,
////            @RequestParam String user_id,
////            @RequestParam MultipartFile image,
////            @RequestParam List<String> knowledge_fields,
////            @RequestParam String description,
////            @RequestParam String instagram,
////            @RequestParam String facebook,
////            @RequestParam String linkedin,
////            @RequestParam String blog,
////            @RequestParam String research_gate,
////            @RequestParam String google_scholar,
////            @RequestParam String sinta,
////            @RequestParam String scopus,
////            @RequestParam String orcid_id,
////            @RequestParam String last_education,
////            @RequestParam String university,
////            @RequestParam String faculty,
////            @RequestParam String department,
////            @RequestParam String year
////
////    ) {
////        try {
////            Optional<Lecturer> checkSlug = lecturerService.findBySlugAndLanguage(slug, lang.toString());
////            if (checkSlug.isPresent()) {
////                ResponseBody response = new ResponseBody(400, "Slug "+slug+" has been used", null);
////                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
////            }
////
////            Optional<Lecturer> checkUser = lecturerService.findByUserIdAndLanguage(user_id, lang.toString());
////            if (checkUser.isPresent()) {
////                ResponseBody response = new ResponseBody(400, "User "+user_id+" has been used", null);
////                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
////            }
////
////            String imageFilename = null;
////            if (image != null) {
////                String filetype = FilenameUtils.getExtension(image.getOriginalFilename());
////                imageFilename = "lecturer-" + utils.generateRandomString(4) + "." + filetype;
////                storageService.saveFile(image, imageFilename, "/media/lecturer");
////            }
////
////            Lecturer lecturer = new Lecturer(
////                    slug, lang, user_id, imageFilename
////                    , knowledge_fields, description, instagram
////                    , facebook, linkedin, blog, research_gate, google_scholar
////                    , sinta, scopus, orcid_id,last_education,university
////                    , faculty,department,year,false, false
////                    , userdata.getIdentity(), userdata.getIdentity()
////            );
////            lecturerService.save(lecturer);
////
////            ResponseBody response = new ResponseBody(200, "Data Insert Successful", lecturer);
////            return new ResponseEntity<>(response, HttpStatus.OK);
////        } catch (Exception e) {
////            ResponseBody response = new ResponseBody(500, e.getMessage(), null);
////            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
////        }
////    }
////
////    @PutMapping("/lecturer/{id}")
////    public ResponseEntity<ResponseBody> editLecturer(
////            @RequestAttribute UserJwtData userdata,
////            @PathVariable("id") String id,
////            @RequestParam String slug,
////            @RequestParam(required = false) MultipartFile image,
////            @RequestParam List<String> knowledge_fields,
////            @RequestParam String description,
////            @RequestParam String instagram,
////            @RequestParam String facebook,
////            @RequestParam String linkedin,
////            @RequestParam String blog,
////            @RequestParam String research_gate,
////            @RequestParam String google_scholar,
////            @RequestParam String sinta,
////            @RequestParam String scopus,
////            @RequestParam String orcid_id,
////            @RequestParam String last_education,
////            @RequestParam String university,
////            @RequestParam String faculty,
////            @RequestParam String department,
////            @RequestParam String year
////    ) {
////        try {
////            Optional<Lecturer> data = lecturerService.findById(id);
////            if (data.isPresent()) {
////                Lecturer _data = data.get();
////                _data.setSlug(slug);
////                _data.setKnowledgeField(knowledge_fields);
////                _data.setDescription(description);
////                _data.setInstagram(instagram);
////                _data.setFacebook(facebook);
////                _data.setLinkedin(linkedin);
////                _data.setBlog(blog);
////                _data.setResearchGate(research_gate);
////                _data.setGoogleScholar(google_scholar);
////                _data.setSinta(sinta);
////                _data.setScopus(scopus);
////                _data.setOrcidId(orcid_id);
////                _data.setLastEducation(last_education);
////                _data.setUniversity(university);
////                _data.setFaculty(faculty);
////                _data.setDepartment(department);
////                _data.setYear(year);
////                if (image != null) {
////                    String filePathString = "uploads/media/lecturer/" +_data.getImage();
////                    File file = new File(filePathString);
////                    if (file.exists()) {
////                        storageService.deleteFile(filePathString);
////                    }
////                    String filetype = FilenameUtils.getExtension(image.getOriginalFilename());
////                    String imageFilename = "lecturer-" + utils.generateRandomString(4) + "." + filetype;
////                    storageService.saveFile(image, imageFilename, "/media/lecturer");
////                    _data.setImage(imageFilename);
////                }
////
////                Lecturer lecturer = lecturerService.save(_data);
////                ResponseBody response = new ResponseBody(200, "Data Updated Successful", lecturer);
////                return new ResponseEntity<>(response, HttpStatus.OK);
////            } else {
////                ResponseBody response = new ResponseBody(200, "Data Updated Successful", null);
////                return new ResponseEntity<>(response, HttpStatus.OK);
////            }
////        } catch (Exception e) {
////            ResponseBody response = new ResponseBody(500, e.getMessage(), null);
////            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
////        }
////    }
////
////    @DeleteMapping("/lecturer/{id}")
////    public ResponseEntity<ResponseBody> deleteLecturer(@RequestAttribute UserJwtData userdata, @PathVariable("id") String id) {
////        try {
////            Optional<Lecturer> data = lecturerService.findById(id);
////
////            if (data.isPresent() && !data.get().getDeleted()) {
////                Lecturer _data = data.get();
////                _data.setDeleted(true);
////                lecturerService.save(_data);
////                String logMessage = "user " + userdata.getIdentity() + " telah menghapus data " + this.getClass().getSimpleName().replace("Controller", "") + " dengan id " + id;
////                logService.save(new Logs(logMessage, "DELETE"));
////                ResponseBody response = new ResponseBody(200, "Delete Successful", null);
////                return new ResponseEntity<>(response, HttpStatus.OK);
////            } else {
////                ResponseBody response = new ResponseBody(404, "Data not found", null);
////                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
////            }
////        } catch (Exception e) {
////            ResponseBody response = new ResponseBody(500, e.getMessage(), null);
////            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
////        }
////    }
////
////    @PostMapping("/lecturer/selected")
////    public ResponseEntity<ResponseBody> selectedArticle(
////            @RequestAttribute UserJwtData userdata,
////            @RequestBody Map<String, ArrayList> lecturerIds
////    ) {
////        try {
////
////            ArrayList ids = lecturerIds.get("lecturerIds");
////
////            for (Object id : ids) {
////                JSONObject object = new JSONObject(id.toString());
////                Optional<Lecturer> data = lecturerService.findById(object.getString("id"));
////                if (data.isPresent()) {
////                    Lecturer _data = data.get();
////                    _data.setSelected(Boolean.parseBoolean(object.getString("value")));
////                    _data.setUpdatedBy(userdata.getIdentity());
////                    lecturerService.save(_data);
////                }
////            }
////            ResponseBody response = new ResponseBody(200, "Data Update Successful", null);
////            return new ResponseEntity<>(response, HttpStatus.CREATED);
////        } catch (Exception e) {
////            ResponseBody response = new ResponseBody(500, e.getMessage(), null);
////            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
////        }
////    }
////
////    @GetMapping("/lecturer/search")
////    public ResponseEntity<ResponseBody> lecturerSearch(
////            @RequestParam String name,
////            @RequestParam (required = false)String lang,
////            @RequestParam(defaultValue = "1") int page,
////            @RequestParam(defaultValue = "3") int limit
////    ) {
////        try {
////            int limits = limit;
////            List<Lecturer> lecturers = new ArrayList<>();
////
////            List<Lecturer> lecturerList;
////            HashMap<String,Object> search;
////            List<HashMap<String,String>> data;
////            if (!name.equalsIgnoreCase("")) {
////                search = generateData.searchLecturer(name);
////                List<String> mapList = (List<String>) search.get("users");
////                data = (List<HashMap<String, String>>) search.get("data"); // get data lecturer list //
////                if (lang == null || lang.equalsIgnoreCase("")) {
////                    lecturerList = lecturerService.findByUserIdIn(mapList);
////                } else {
////                    lecturerList = lecturerService.findByUserIdInAndLanguage(mapList, lang);
////                }
////            } else {
////                data = generateData.lecturerList();
////                if (lang == null || lang.equalsIgnoreCase("")) {
////                    lecturerList = lecturerService.findAll();
////                } else {
////                    lecturerList = lecturerService.findByLanguage(lang);
////                }
////            }
////
////            for (Lecturer lecturer: lecturerList) {
////                for (int i=0;i<data.size();i++) {
////                    HashMap<String, String> hashMap = data.get(i);
////                    if (hashMap.get("user_id").equalsIgnoreCase(lecturer.getUserId())) {
////                        String imageLink = lecturer.getImage() == null ? null : this.baseUrl() + "/uploads/media/lecturer/" + lecturer.getImage();
////                        lecturer.setProfile(hashMap);
////                        lecturer.setImage(imageLink);
////                        lecturers.add(lecturer);
////                    }
////                }
////            }
////
////            List<Lecturer> sampleDataList = lecturers.stream()
////                    .skip( page > 0 ? ( ( page - 1 ) * limits ) : 0 )
////                    .limit(limits)
////                    .collect(Collectors.toList());
////
////            long count = lecturers.size();
////            Map<String, Object> lecturerResponse = new HashMap<>();
////            lecturerResponse.put("data", sampleDataList);
////            lecturerResponse.put("currentPage", page);
////            lecturerResponse.put("totalItems", count);
////            lecturerResponse.put("totalPages", count/limits);
////            ResponseBody response = new ResponseBody(200, "Data Successfully Retrieved",lecturerResponse);
////            return new ResponseEntity<>(response, HttpStatus.OK);
////        } catch (Exception e) {
////            ResponseBody response = new ResponseBody(500, e.getMessage(), null);
////            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
////        }
////    }
////
////    @PostMapping("/lecturer/input-all")
////    public ResponseEntity<ResponseBody> inputAllLecturers(
////            @RequestParam(required = false) Language lang,
////            @RequestAttribute UserJwtData userdata,
////            @RequestParam(required = false, defaultValue = "0,2,4") String type_id,
////            @RequestParam(required = false, defaultValue = "Izin Belajar,Aktif,Tugas Belajar") String status
////    ) {
////        try {
////            // get all lecturers //
////            String url = "https://api.usu.ac.id/0.1/users?type_id="+type_id+"&status="+status+"&fieldset=education";
////            JSONObject result   = generateData.restTemplate(url);
////            JSONArray jsonData  = result.getJSONArray("data");
////
////            List<Lecturer> lecturerList = new ArrayList<>();
////            for (int i=0;i<jsonData.length();i++) {
////                JSONObject jsonObject = jsonData.getJSONObject(i);
////                String userId = jsonObject.getString("id");
////                // check lecturer based language //
////                Optional<Lecturer> checkLecturer = lecturerService.findByUserIdAndLanguage(userId, lang.toString());
////                if (checkLecturer.isEmpty()) {
////                    HashMap<String, String> hashMap = generateData.lecturer(userId);
////                    String lecturerNameWithDegree = hashMap.get("name");
////                    String lecturerName = hashMap.get("full_name");
////                    String slug = lecturerNameWithDegree.replaceAll("\\p{Punct}", "").replace(" ", "-").toLowerCase().trim();
////
////                    JSONArray jsonArray = jsonObject.getJSONArray("education");
////                    String description;
////                    String institutionName = "-";
////                    String educationName = "-";
////                    String department = "-";
////                    String entryYear = "-";
////                    if (jsonArray.length() != 0) {
////                        List<HashMap<String, String>> mapList = new ArrayList<>();
////                        for (int j = 0; j < jsonArray.length(); j++) {
////                            JSONObject object = jsonArray.getJSONObject(j);
////
////                            HashMap<String, String> education = new HashMap<>();
////                            education.put("education_code", object.getString("education_code"));
////                            education.put("institution_name", object.getString("institution_name"));
////                            education.put("department", object.getString("department"));
////                            education.put("enter_year", object.getString("enter_year"));
////                            education.put("user_id", object.getString("lecturer_id"));
////
////                            mapList.add(education);
////                        }
////                        Collections.sort(mapList, new Comparator<HashMap<String, String>>() {
////                            public int compare(HashMap<String, String> one, HashMap<String, String> two) {
////                                return two.get("enter_year").compareTo(one.get("enter_year"));
////                            }
////                        });
////                        System.out.println(mapList);
////                        HashMap<String, String> map = mapList.get(0);
////                        entryYear = map.get("enter_year");
////                        department = map.get("department");
////                        System.out.println(entryYear);
////
////                        String institution = map.get("institution_name");
////                        if (institution.toLowerCase().contains("usu")) {
////                            institutionName = "Universitas Sumatera Utara";
////                        } else {
////                            institutionName = institution;
////                        }
////
////                        String educationCode    = map.get("education_code");
////                        if (educationCode.equalsIgnoreCase("S1")) {
////                            if (lang.toString().equalsIgnoreCase("en")){
////                                educationName = "Bachelor";
////                            } else {
////                                educationName = "Sarjana";
////                            }
////                        } else if (educationCode.equalsIgnoreCase("S2")) {
////                            if (lang.toString().equalsIgnoreCase("en")){
////                                educationName = "Master";
////                            } else {
////                                educationName = "Magister";
////                            }
////                        } else if (educationCode.equalsIgnoreCase("S3")) {
////                            if (lang.toString().equalsIgnoreCase("en")){
////                                educationName = "Doctoral";
////                            } else {
////                                educationName = "Doktoral";
////                            }
////                        } else if (educationCode.equalsIgnoreCase("SP1") || educationCode.equalsIgnoreCase("SP2")) {
////                            if (lang.toString().equalsIgnoreCase("en")){
////                                educationName = "Spesialist";
////                            } else {
////                                educationName = "Spesialis";
////                            }
////                        } else if (educationCode.equalsIgnoreCase("D3")) {
////                            educationName = "Diploma";
////                        } else if (educationCode.equalsIgnoreCase("PROFESI")) {
////                            if (lang.toString().equalsIgnoreCase("en")){
////                                educationName = "Profession";
////                            } else {
////                                educationName = "Profesi";
////                            }
////                        } else {
////                            educationName = educationCode;
////                        }
////
////                        if (lang.toString().equalsIgnoreCase("en")) {
////                            description = lecturerName+" is a lecturer at Universitas Sumatera Utara (USU). A "+educationName+" of "+institutionName+" on "+entryYear+".";
////                        } else {
////                            description = lecturerName+" adalah seorang dosen di Universitas Sumatera Utara (USU). Beliau merupakan lulusan "+educationName+" di "+institutionName+" pada tahun "+entryYear+".";
////                        }
////                    } else {
////                        if (lang.toString().equalsIgnoreCase("en")) {
////                            description = lecturerName+" is a lecturer at Universitas Sumatera Utara (USU).";
////                        } else {
////                            description = lecturerName+" adalah seorang dosen di Universitas Sumatera Utara (USU).";
////                        }
////                    }
////
////                    Lecturer lecturer = new Lecturer(
////                            slug,
////                            lang,
////                            userId,
////                            null,
////                            new ArrayList<>(),
////                            description,
////                            "-",
////                            "-",
////                            "-",
////                            "-",
////                            "-",
////                            "-",
////                            "-",
////                            "-",
////                            "-",
////                            educationName,
////                            institutionName,
////                            department,
////                            "-",
////                            entryYear,
////                            false,
////                            false,
////                            userdata.getIdentity(),
////                            userdata.getIdentity()
////                    );
////
////                    lecturerList.add(lecturer);
////                }
////            }
////            lecturerService.saveAll(lecturerList);
////
////            ResponseBody response = new ResponseBody(200, "Data Insert Successful "+lecturerList.size()+" Lecturers", lecturerList);
////            return new ResponseEntity<>(response, HttpStatus.OK);
////        } catch (Exception e) {
////            ResponseBody response = new ResponseBody(500, e.getMessage(), null);
////            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
////        }
////    }
////
////    @PutMapping("/lecturer/photo-null")
////    public ResponseEntity<ResponseBody> inputAllLecturers(
////            @RequestParam(required = false) String lang,
////            @RequestAttribute UserJwtData userdata
////    ) {
////        try {
////            List<Lecturer> lecturers = lecturerService.findByLanguage(lang);
////            lecturers.forEach(e->{
////                String image = e.getImage();
////                if (image != null && image.equalsIgnoreCase("")) {
////                    e.setImage(null);
////                    e.setUpdatedBy(userdata.getIdentity());
////                }
////            });
////            lecturerService.saveAll(lecturers);
////
////            ResponseBody response = new ResponseBody(200, "Data "+lecturers.size()+" Lecturers Updated", lecturers);
////            return new ResponseEntity<>(response, HttpStatus.OK);
////        } catch (Exception e) {
////            ResponseBody response = new ResponseBody(500, e.getMessage(), null);
////            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
////        }
////    }
//}
