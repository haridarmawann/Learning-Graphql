package hari.core.graphql.helper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@Transactional
public class GenerateData {

    public JSONObject restTemplate(String url) throws JSONException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        String request = restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody();

        return new JSONObject(request);
    }

    public List<HashMap<String, String>> faculties() throws JSONException {
        JSONObject result = this.restTemplate("https://akademik.usu.ac.id/api/data/faculties");

        if (result.isNull("data")) {
            throw new IllegalArgumentException("faculties is not exist");
        }

        JSONArray jsonData = result.getJSONArray("data");

        List<HashMap<String, String>> hashList = new ArrayList<>();
        for (int i = 0; i < jsonData.length(); i++) {
            JSONObject jsonObject = jsonData.getJSONObject(i);
            String faculty = jsonObject.getString("name");
            String slug = faculty.toLowerCase().replace(" ", "-");

            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("faculty_code", jsonObject.getString("faculty_id"));
            hashMap.put("faculty_name", faculty);
            hashMap.put("slug", slug);
            hashList.add(hashMap);
        }
        return hashList;
    }

    public List<HashMap<String, Object>> educationFee(String major) throws JSONException {
        JSONObject result = this.restTemplate("https://keuangan.usu.ac.id/api/reference/education-fees/" + major);

        if (result.isNull("data")) {
            throw new IllegalArgumentException("major code " + major + " is not exist");
        }

        JSONArray jsonData = result.getJSONArray("data");

        List<HashMap<String, Object>> hashList = new ArrayList<>();
        for (int i = 0; i < jsonData.length(); i++) {
            JSONObject jsonObject = jsonData.getJSONObject(i);

            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("id", jsonObject.getString("major_code"));
            hashMap.put("name", jsonObject.getString("major_name"));
            hashMap.put("class", jsonObject.getString("class_type_name"));
            hashMap.put("education_level", jsonObject.getString("education_level"));
            hashMap.put("education_level_name", jsonObject.getString("education_level_name"));

            JSONArray jsonArray = jsonObject.getJSONArray("bills_fee_list");
            List<HashMap<String, String>> bills = new ArrayList<>();
            for (int j = 0; j < jsonArray.length(); j++) {
                JSONObject data = jsonArray.getJSONObject(j);

                HashMap<String, String> hashMapBill = new HashMap<>();
                hashMapBill.put("type", data.getString("fee_type_name"));
                hashMapBill.put("level", data.getString("level"));
                hashMapBill.put("amount", data.getString("amount"));
                bills.add(hashMapBill);
            }

            hashMap.put("fee", bills);
            hashList.add(hashMap);
        }
        return hashList;
    }

    public List<HashMap<String, String>> profile() throws JSONException {
        JSONObject result = this.restTemplate("https://api.usu.ac.id/0.1/users?type_id=0,2,4&status=Izin Belajar,Aktif,Tugas Belajar");
//        if (result.isNull("data")) {
//            throw new IllegalArgumentException("lecturer code "+ userId+" is not exist");
//
//        }
        JSONArray jsonData = result.getJSONArray("data");
        List<HashMap<String, String>> hashList = new ArrayList<>();
        for (int i = 0; i < jsonData.length(); i++) {
            JSONObject jsonObject = jsonData.getJSONObject(i);
            String name = "";
            if (jsonObject.getString("front_degree").isBlank()) {
                name = jsonObject.getString("full_name").trim() + ", " + jsonObject.getString("behind_degree").trim();
            } else {
                name = jsonObject.getString("front_degree").trim() + " " + jsonObject.getString("full_name").trim() + ", " + jsonObject.getString("behind_degree").trim();
            }

            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("id", jsonObject.getString("id"));
            hashMap.put("name", name);
            hashList.add(hashMap);
        }
        return hashList;
    }

    public HashMap<String, Object> searchLecturer(String param) throws JSONException {
        JSONObject result = this.restTemplate("https://api.usu.ac.id/0.1/users/search?query=" + param);
        JSONObject jsonRes = result.getJSONObject("response");
        JSONArray jsonData = jsonRes.getJSONArray("data");
        List<String> hashList = new ArrayList<>();
        List<HashMap<String, String>> hashMapList = new ArrayList<>();
        for (int i = 0; i < jsonData.length(); i++) {
            JSONObject jsonObject = jsonData.getJSONObject(i);
            hashList.add(jsonObject.getString("id"));

            String name = "";
            if (jsonObject.getString("front_degree").isBlank()) {
                name = jsonObject.getString("full_name").trim() + ", " + jsonObject.getString("behind_degree").trim();
            } else {
                name = jsonObject.getString("front_degree").trim() + " " + jsonObject.getString("full_name").trim() + ", " + jsonObject.getString("behind_degree").trim();
            }
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("user_id", jsonObject.getString("id"));
            hashMap.put("name", name);
            hashMap.put("full_name", jsonObject.getString("full_name"));
            hashMap.put("work_name", jsonObject.getString("work_unit"));
            hashMap.put("photo", jsonObject.getString("photo"));
            hashMapList.add(hashMap);
        }

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("users", hashList);
        hashMap.put("data", hashMapList);

        return hashMap;
    }

    public List<HashMap<String, String>> lecturerList() throws JSONException {
        JSONObject result = this.restTemplate("https://api.usu.ac.id/0.1/users?type_id=0,2,4&status=Izin Belajar,Aktif,Tugas Belajar");

        JSONArray jsonData = result.getJSONArray("data");
        List<HashMap<String, String>> hashList = new ArrayList<>();
        for (int i = 0; i < jsonData.length(); i++) {
            JSONObject jsonObject = jsonData.getJSONObject(i);
            String name = "";
            if (jsonObject.getString("front_degree").isBlank()) {
                name = jsonObject.getString("full_name").trim() + ", " + jsonObject.getString("behind_degree").trim();
            } else {
                name = jsonObject.getString("front_degree").trim() + " " + jsonObject.getString("full_name").trim() + ", " + jsonObject.getString("behind_degree").trim();
            }

            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("user_id", jsonObject.getString("id"));
            hashMap.put("sister_uuid", jsonObject.isNull("sister_uuid") ? null : jsonObject.getString("sister_uuid"));
            hashMap.put("name", name);
            hashMap.put("full_name", jsonObject.getString("full_name"));
            hashMap.put("nip", jsonObject.getString("nip"));
            hashMap.put("nidn", jsonObject.getString("nidn"));
            hashMap.put("email_usu", jsonObject.getString("email_usu"));
            hashMap.put("work_name", jsonObject.getString("work_unit_name"));
            hashMap.put("photo", jsonObject.getString("photo"));
            hashList.add(hashMap);
        }
        return hashList;
    }

    public List<HashMap<String, String>> userList(String paramWork, String paramName) throws JSONException {
        JSONObject result = this.restTemplate("https://api.usu.ac.id/0.1/users?type_id=0,2,4&status=Izin Belajar,Aktif,Tugas Belajar");
        JSONArray jsonData = result.getJSONArray("data");
        List<HashMap<String, String>> hashList = new ArrayList<>();
        for (int i = 0; i < jsonData.length(); i++) {
            JSONObject jsonObject = jsonData.getJSONObject(i);
            String workName = jsonObject.getString("work_unit_name");
            String fullName = jsonObject.getString("full_name");

            if (workName.toLowerCase().contains(paramWork.toLowerCase()) && fullName.toLowerCase().contains(paramName)) {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("id", jsonObject.getString("id"));
                hashMap.put("full_name", fullName);
                hashMap.put("work_name", workName);
                hashMap.put("work_id", jsonObject.getString("work_unit_id"));
                hashMap.put("photo", jsonObject.getString("photo"));
                hashList.add(hashMap);
            }
        }
        return hashList;
    }

    public HashMap<String, String> lecturer(String userId) throws JSONException {
        JSONObject result = this.restTemplate("https://api.usu.ac.id/0.1/users/" + userId);
        if (result.isNull("data")) {
            throw new IllegalArgumentException("lecturer code " + userId + " is not exist");

        }
        JSONObject jsonObject = result.getJSONObject("data");
        String name = "";
        if (jsonObject.getString("front_degree").isBlank()) {
            name = jsonObject.getString("full_name").trim() + ", " + jsonObject.getString("behind_degree").trim();
        } else {
            name = jsonObject.getString("front_degree").trim() + " " + jsonObject.getString("full_name").trim() + ", " + jsonObject.getString("behind_degree").trim();
        }

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("sister_uuid", jsonObject.isNull("sister_uuid") ? null : jsonObject.getString("sister_uuid"));
        hashMap.put("name", name);
        hashMap.put("full_name", jsonObject.getString("full_name"));
        hashMap.put("nip", jsonObject.getString("nip"));
        hashMap.put("nidn", jsonObject.getString("nidn"));
        hashMap.put("email_usu", jsonObject.getString("email_usu"));
        hashMap.put("work_name", jsonObject.getString("work_unit_name"));
        hashMap.put("photo", jsonObject.getString("photo"));

        return hashMap;
    }

    public List<HashMap<String, String>> teaching(String nip, String lang) throws JSONException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        String request = restTemplate.exchange("https://feeder.usu.ac.id/feeder/api/DirDosen?nip=" + nip, HttpMethod.GET, entity, String.class).getBody();

        JSONArray jsonData = new JSONArray(request);
        List<HashMap<String, String>> hashList = new ArrayList<>();
        for (int i = 0; i < jsonData.length(); i++) {
            JSONObject jsonObject = jsonData.getJSONObject(i);
            String mk = "";
            if (lang.equalsIgnoreCase("id")) {
                mk = jsonObject.getString("NAMAMK");
            } else {
                mk = jsonObject.getString("ALIASMK");
            }

            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("subject", mk);
            hashMap.put("major_name", jsonObject.getString("NAMAPRODI"));
            hashMap.put("semester", jsonObject.getString("SEMMK"));
            hashMap.put("class", jsonObject.getString("KELASMK"));
            hashList.add(hashMap);
        }
        hashList.sort(Comparator.comparing(o -> Integer.parseInt(o.get("semester")), Comparator.reverseOrder()));

        return hashList;
    }

    public String requestSisterToken() throws JSONException {
        HashMap<String, String> body = new HashMap<>();
        body.put("username", "73l5O1pJvkS5WerUczooYB/irvVfMM9h3dT6QH1Wl8U=");
        body.put("password", "Towrt5gC8zhDoF+IcVimKs7Ywr3ljYF8Se/El2HSL0qFxma0qrQ1g2eBqDpfGels");
        body.put("id_pengguna", "dfb9244b-1623-458a-a1cb-ba45072a7617");
        JSONObject object = this.hitSisterEndpoint(body);
        return object.get("token").toString();
    }

    private JSONObject hitSisterEndpoint(HashMap<String, String> body) throws JSONException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<HashMap<String, String>> json = new HttpEntity<>(body, headers);
        String request = restTemplate.exchange("http://sister.usu.ac.id/ws.php/1.0/authorize", HttpMethod.POST, json, String.class).getBody();
        return new JSONObject(request);
    }

    private HttpEntity<HashMap<String, Object>> setHeaderSister() throws JSONException {
        HttpHeaders tokenHeader = new HttpHeaders();
        tokenHeader.set("Authorization", "Bearer " + this.requestSisterToken());
        HashMap<String, Object> requestBody = new HashMap<>();
        return new HttpEntity<>(requestBody, tokenHeader);
    }

    public List<HashMap<String, String>> publications(String sisterId) throws JSONException {
        RestTemplate restTemplate = new RestTemplate();
        String request = restTemplate.exchange("http://sister.usu.ac.id/ws.php/1.0/publikasi?id_sdm=" + sisterId, HttpMethod.GET, this.setHeaderSister(), String.class).getBody();

        JSONArray jsonData = new JSONArray(request);

        List<HashMap<String, String>> hashList = new ArrayList<>();
        for (int i = 0; i < jsonData.length(); i++) {
            JSONObject jsonObject = jsonData.getJSONObject(i);
            String year = null;
            if (!jsonObject.isNull("tanggal")) {
                year = jsonObject.getString("tanggal").split("-")[0];
            }

            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("id", jsonObject.getString("id"));
            hashMap.put("title", jsonObject.isNull("judul") ? null : jsonObject.getString("judul"));
            hashMap.put("type", jsonObject.isNull("jenis_publikasi") ? null : jsonObject.getString("jenis_publikasi"));
            hashMap.put("year", year);
            hashList.add(hashMap);
        }
        hashList.sort(Comparator.comparing(o -> o.get("year") == null ? 0 : Integer.parseInt(o.get("year")), Comparator.reverseOrder()));

        return hashList;
    }

    public List<HashMap<String, String>> research(String sisterId) throws JSONException {
        RestTemplate restTemplate = new RestTemplate();
        String request = restTemplate.exchange("http://sister.usu.ac.id/ws.php/1.0/penelitian?id_sdm=" + sisterId, HttpMethod.GET, this.setHeaderSister(), String.class).getBody();

        JSONArray jsonData = new JSONArray(request);
        List<HashMap<String, String>> hashList = new ArrayList<>();
        for (int i = 0; i < jsonData.length(); i++) {
            JSONObject jsonObject = jsonData.getJSONObject(i);

            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("id", jsonObject.getString("id"));
            hashMap.put("title", jsonObject.isNull("judul") ? null : jsonObject.getString("judul"));
            hashMap.put("year", jsonObject.isNull("tahun_pelaksanaan") ? null : jsonObject.getString("tahun_pelaksanaan"));
            hashList.add(hashMap);
        }
        hashList.sort(Comparator.comparing(o -> o.get("year") == null ? 0 : Integer.parseInt(o.get("year")), Comparator.reverseOrder()));

        return hashList;
    }


    public List<HashMap<String, String>> devotion(String sisterId) throws JSONException {
        RestTemplate restTemplate = new RestTemplate();
        String request = restTemplate.exchange("http://sister.usu.ac.id/ws.php/1.0/pengabdian?id_sdm=" + sisterId, HttpMethod.GET, this.setHeaderSister(), String.class).getBody();

        JSONArray jsonData = new JSONArray(request);
        List<HashMap<String, String>> hashList = new ArrayList<>();
        for (int i = 0; i < jsonData.length(); i++) {
            JSONObject jsonObject = jsonData.getJSONObject(i);

            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("id", jsonObject.getString("id"));
            hashMap.put("title", jsonObject.isNull("judul") ? null : jsonObject.getString("judul"));
            hashMap.put("year", jsonObject.isNull("tahun_pelaksanaan") ? null : jsonObject.getString("tahun_pelaksanaan"));
            hashList.add(hashMap);
        }
        hashList.sort(Comparator.comparing(o -> o.get("year") == null ? 0 : Integer.parseInt(o.get("year")), Comparator.reverseOrder()));

        return hashList;
    }

    public List<HashMap<String, Object>> hki(String sisterId) throws JSONException {
        RestTemplate restTemplate = new RestTemplate();
        String request = restTemplate.exchange("http://sister.usu.ac.id/ws.php/1.0/kekayaan_intelektual?id_sdm=" + sisterId, HttpMethod.GET, this.setHeaderSister(), String.class).getBody();

        JSONArray jsonData = new JSONArray(request);
        List<HashMap<String, Object>> hashList = new ArrayList<>();
        for (int i = 0; i < jsonData.length(); i++) {
            JSONObject jsonObject = jsonData.getJSONObject(i);

            HashMap<String, Object> hashMap = this.hkiDetail(jsonObject.getString("id"));
            hashList.add(hashMap);
        }
        hashList.sort(Comparator.comparing(o -> o.get("year") == null ? 0 : Integer.parseInt(o.get("year").toString()), Comparator.reverseOrder()));

        return hashList;
    }

    public HashMap<String, Object> hkiDetail(String id) throws JSONException {
        RestTemplate restTemplate = new RestTemplate();
        String request = restTemplate.exchange("http://sister.usu.ac.id/ws.php/1.0/kekayaan_intelektual/" + id, HttpMethod.GET, this.setHeaderSister(), String.class).getBody();

        JSONObject jsonObject = new JSONObject(request);
        String year = null;
        if (!jsonObject.isNull("tanggal")) {
            year = jsonObject.getString("tanggal").split("-")[0];
        }

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("id", jsonObject.getString("id"));
        hashMap.put("title", jsonObject.isNull("judul") ? null : jsonObject.getString("judul"));
        hashMap.put("type", jsonObject.isNull("jenis_publikasi") ? null : jsonObject.getString("jenis_publikasi"));
        hashMap.put("year", year);

        JSONArray jsonArray = jsonObject.getJSONArray("penulis");
        if (jsonArray.length() != 0) {
            List<String> inventors = new ArrayList<>();
            for (int j = 0; j < jsonArray.length(); j++) {
                JSONObject object = jsonArray.getJSONObject(j);

                if (object.getString("peran").toLowerCase().contains("penemu")) {
                    inventors.add(object.getString("nama"));
                }
            }
            hashMap.put("inventor", inventors);
        }

        return hashMap;
    }

    public HashMap<String, String> publicationDetail(String sisterId, String pubId) throws JSONException {
        final HashMap<String, String>[] hashMap = new HashMap[]{new HashMap<>()};

        List<HashMap<String, String>> list = this.publications(sisterId);
        list.forEach(pub -> {
            if (pub.get("id").equalsIgnoreCase(pubId)) {
                hashMap[0] = pub;
            }
        });

        return hashMap[0];
    }
}

//    public String templatingCv(Lecturer lecturer, String template, String lang) {
//        if (lang.equalsIgnoreCase("en")) {
//            template = template.replace("{education_title}", "EDUCATION");
//            template = template.replace("{short_description_title}", "SHORT DESCRIPTION");
//            template = template.replace("{knowledge_fields_title}", "Knowledge fields");
//            template = template.replace("{social_media_title}", "SOCIAL MEDIA");
//            template = template.replace("{research_media_title}", "RESEARCH MEDIA");
//            template = template.replace("{research_title}", "RESEARCH");
//            template = template.replace("{publication_title}", "PUBLICATION");
//            template = template.replace("{hki_title}", "PATENTS & INTELLECTUAL PROPERTY RIGHTS");
//            template = template.replace("{devotion_title}", "DEVOTION");
//            template = template.replace("{achievement_title}", "ACHIEVEMENT");
//        } else {
//            template = template.replace("{education_title}", "PENDIDIKAN");
//            template = template.replace("{short_description_title}", "DESKRIPSI SINGKAT");
//            template = template.replace("{knowledge_fields_title}", "Bidang ilmu");
//            template = template.replace("{social_media_title}", "MEDIA SOSIAL");
//            template = template.replace("{research_media_title}", "MEDIA PENELITIAN");
//            template = template.replace("{research_title}", "PENELITIAN");
//            template = template.replace("{publication_title}", "PUBLIKASI");
//            template = template.replace("{hki_title}", "PATEN & HKI");
//            template = template.replace("{devotion_title}", "PENGABDIAN");
//            template = template.replace("{achievement_title}", "PENGHARGAAN");
//        }
//
//
//        template = template.replace("{name}", lecturer.getProfile().get("name") == null ? "-" : lecturer.getProfile().get("name"));
//        template = template.replace("{nip}", lecturer.getProfile().get("nip") == null ? "" : "NIP : "+lecturer.getProfile().get("nip"));
//        template = template.replace("{nidn}", lecturer.getProfile().get("nidn") == null ? "" : " | NIDN : "+lecturer.getProfile().get("nidn"));
//        template = template.replace("{email}", lecturer.getProfile().get("email_usu") == null ? "" : " | Email : "+lecturer.getProfile().get("email_usu"));
//
//        template = template.replace("{edu_university}", lecturer.getUniversity());
//        template = template.replace("{edu_faculty}", lecturer.getFaculty());
//        template = template.replace("{edu_department}", lecturer.getDepartment());
//        template = template.replace("{edu_year}", lecturer.getYear());
//
//        template = template.replace("{knowledge_fields}", lecturer.getKnowledgeField() == null ? "-" : lecturer.getKnowledgeField().toString().replace("[", "").replace("]", ""));
//        template = template.replace("{short_description}", lecturer.getDescription() == null ? "-" : lecturer.getDescription());
//
//        template = template.replace("{research_gate}", lecturer.getResearchGate() == null ? "-" : lecturer.getResearchGate());
//        template = template.replace("{google_scholar}", lecturer.getGoogleScholar() == null ? "-" : lecturer.getGoogleScholar());
//        template = template.replace("{sinta}", lecturer.getSinta() == null ? "-" : lecturer.getSinta());
//        template = template.replace("{scopus}", lecturer.getScopus() == null ? "-" : lecturer.getScopus());
//        template = template.replace("{orcid}", lecturer.getOrcidId() == null ? "-" : lecturer.getOrcidId());
//
//        template = template.replace("{facebook}", lecturer.getFacebook() == null ? "-" : lecturer.getFacebook());
//        template = template.replace("{instagram}", lecturer.getInstagram() == null ? "-" : lecturer.getInstagram());
//        template = template.replace("{linkedin}", lecturer.getLinkedin() == null ? "-" : lecturer.getLinkedin());
//        template = template.replace("{blog}", lecturer.getBlog() == null ? "-" : lecturer.getBlog());
//
//        template = template.replace("{research_table}", this.templatingResearch(lecturer.getResearch()));
//        template = template.replace("{devotion_table}", this.templatingResearch(lecturer.getDevotion()));
//        template = template.replace("{publication_table}", this.templatingPublication(lecturer.getPublish()));
//        template = template.replace("{hki_table}", this.templatingHki(lecturer.getHki()));
//        template = template.replace("{achievement_table}", this.templatingAchievement(lecturer.getAchievement()));
//
//        return template;
//    }
//
//    public String templatingResearch(List<HashMap<String,String>> data) {
//        final String[] itemRow = {""};
//        if (data.isEmpty()) {
//            itemRow[0] += "-";
//        }
//        data.forEach(item-> {
//            itemRow[0] += "<tr>\n" +
//                    "           <td>"+item.get("title")+"</td>\n" +
//                    "           <td class=\"td-right-pen\">"+item.get("year")+"</td>\n" +
//                    "      </tr>";
//        });
//        return itemRow[0];
//    }
//
//    public String templatingPublication(List<LecturerPublication> data) {
//        final String[] itemRow = {""};
//        if (data.isEmpty()) {
//            itemRow[0] += "-";
//        }
//        data.forEach(item-> {
//            String getLink = "";
//            String urlLink = item.getLink();
//            if (urlLink != null) {
//                getLink = "<br>\n" +
//                        "   <a href=\""+urlLink+"\"><i>"+urlLink+"</i></a>";
//            }
//
//            String year = "";
//            if (item.getPublication().get("year") != null) {
//                year = item.getPublication().get("year");
//            }
//
//            itemRow[0] += "<tr>\n" +
//                    "       <td width=\"65%\">\n" +
//                    "           "+item.getPublication().get("title")+"\n" +
//                    "           "+getLink+"</td>\n" +
//                    "       <td class=\"td-right-pen\">\n" +
//                    "           "+item.getPublication().get("type")+"\n" +
//                    "           <br>\n" +
//                    "           "+year+"\n" +
//                    "       </td>\n" +
//                    "   </tr>";
//        });
//        return itemRow[0];
//    }
//
//    public String templatingHki(List<HashMap<String,Object>> data) {
//        final String[] itemRow = {""};
//        if (data.isEmpty()) {
//            itemRow[0] += "-";
//        }
//        data.forEach(item-> {
//
//            itemRow[0] += "<tr>\n" +
//                    "       <td width=\"75%\">"+item.get("title").toString().toUpperCase()+
//                    "           <br>" +
//                    "           <div class=\"hki\">Penemu : "+ item.get("inventor").toString().replace("[","").replace("]","") +
//                    "           </td>\n" +
//                    "           <td class=\"td-right-pen\">" +item.get("type")+
//                    "               <br>\n" +item.get("year")+
//                    "       </td>\n" +
//                    "       </tr>";
//        });
//        return itemRow[0];
//    }
//
//    public String templatingAchievement(List<LecturerAchievement> data) {
//        final String[] itemRow = {""};
//        if (data.isEmpty()) {
//            itemRow[0] += "-";
//        }
//        data.forEach(item-> {
//            String content = item.getTitle() + ", " + item.getPlace() + ", " + item.getDate().toString().split(" ")[5];
//            itemRow[0] += "<tr>" +
//                    "       <td>"+content+"</td>" +
//                    "   </tr>";
//        });
//        return itemRow[0];
//    }
//}
