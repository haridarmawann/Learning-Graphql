package hari.core.graphql.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import hari.core.graphql.enumerate.Language;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.HashMap;
import java.util.List;


    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    @Document("lecturer")
    public class Lecturer {

        @Id
        private String id;
        @Field("slug")
        private String slug;
        @Field("language")
        private Language language;
        @Field("user_id")
        private String userId;
        @Transient
        @Field("profile")
        private HashMap<String,String> profile;
//        @Field("image")
//        private String image;
        @Field("knowledge_field")
        private List<String> knowledgeField;
        @Field("description")
        private String description;
        @Field("instagram")
        private String instagram;
        @Field("facebook")
        private String facebook;
        @Field("linkedin")
        private String linkedin;
        @Field("blog")
        private String blog;
        @Field("research_gate")
        private String researchGate;
        @Field("google_scholar")
        private String googleScholar;
        @Field("sinta")
        private String sinta;
        @Field("scopus")
        private String scopus;
        @Field("orcid_id")
        private String orcidId;

        @Field("achievement")
        private List<LecturerAchievement> achievement;

        @Field("video")
        private List<LecturerVideo> video;

        private List<HashMap<String,String>> research;
        private List<LecturerPublication> publish;
        private List<HashMap<String,String>> teaching;
        private List<HashMap<String,String>> devotion;
        private List<HashMap<String,Object>> hki;
        @Field("last_education")
        private String lastEducation;
        @Field("university")
        private String university;
        @Field("faculty")
        private String faculty;
        @Field("department")
        private String department;
        @Field("year")
        private String year;
        @Field("is_deleted")
        private Boolean isDeleted;
        @Field("is_selected")
        private Boolean isSelected;
        @CreatedDate
        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        @Field("created_at")
        private Date createdAt;
        @LastModifiedDate
        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        @Field("updated_at")
        private Date updatedAt;

        public Lecturer() {
        }

        public Lecturer(String slug, Language language, String userId, List<String> knowledgeField, String description, String instagram, String facebook, String linkedin, String blog, String researchGate, String googleScholar, String sinta, String scopus, String orcidId, String lastEducation, String university, String faculty, String department, String year, Boolean isDeleted, Boolean isSelected) {
            this.slug = slug;
            this.language = language;
            this.userId = userId;
            this.knowledgeField = knowledgeField;
            this.description = description;
            this.instagram = instagram;
            this.facebook = facebook;
            this.linkedin = linkedin;
            this.blog = blog;
            this.researchGate = researchGate;
            this.googleScholar = googleScholar;
            this.sinta = sinta;
            this.scopus = scopus;
            this.orcidId = orcidId;
            this.lastEducation = lastEducation;
            this.university = university;
            this.department = department;
            this.faculty = faculty;
            this.year = year;
            this.isDeleted = isDeleted;
            this.isSelected = isSelected;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

        public Language getLanguage() {
            return language;
        }

        public void setLanguage(Language language) {
            this.language = language;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public HashMap<String, String> getProfile() {
            return profile;
        }

        public void setProfile(HashMap<String, String> profile) {
            this.profile = profile;
        }

        public List<String> getKnowledgeField() {
            return knowledgeField;
        }

        public void setKnowledgeField(List<String> knowledgeField) {
            this.knowledgeField = knowledgeField;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getInstagram() {
            return instagram;
        }

        public void setInstagram(String instagram) {
            this.instagram = instagram;
        }

        public String getFacebook() {
            return facebook;
        }

        public void setFacebook(String facebook) {
            this.facebook = facebook;
        }

        public String getLinkedin() {
            return linkedin;
        }

        public void setLinkedin(String linkedin) {
            this.linkedin = linkedin;
        }

        public String getBlog() {
            return blog;
        }

        public void setBlog(String blog) {
            this.blog = blog;
        }

        public String getResearchGate() {
            return researchGate;
        }

        public void setResearchGate(String researchGate) {
            this.researchGate = researchGate;
        }

        public String getGoogleScholar() {
            return googleScholar;
        }

        public void setGoogleScholar(String googleScholar) {
            this.googleScholar = googleScholar;
        }

        public String getSinta() {
            return sinta;
        }

        public void setSinta(String sinta) {
            this.sinta = sinta;
        }

        public String getScopus() {
            return scopus;
        }

        public void setScopus(String scopus) {
            this.scopus = scopus;
        }

        public String getOrcidId() {
            return orcidId;
        }

        public void setOrcidId(String orcidId) {
            this.orcidId = orcidId;
        }

        public String getLastEducation() {
            return lastEducation;
        }

        public void setLastEducation(String lastEducation) {
            this.lastEducation = lastEducation;
        }

        public String getUniversity() {
            return university;
        }

        public void setUniversity(String university) {
            this.university = university;
        }

        public String getFaculty() {
            return faculty;
        }

        public void setFaculty(String faculty) {
            this.faculty = faculty;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public List<LecturerAchievement> getAchievement() {
            return achievement;
        }

        public void setAchievement(List<LecturerAchievement> achievement) {
            this.achievement = achievement;
        }

        public List<LecturerVideo> getVideo() {
            return video;
        }

        public void setVideo(List<LecturerVideo> video) {
            this.video = video;
        }

        public List<HashMap<String, String>> getResearch() {
            return research;
        }

        public void setResearch(List<HashMap<String, String>> research) {
            this.research = research;
        }

        public List<LecturerPublication> getPublish() {
            return publish;
        }

        public void setPublish(List<LecturerPublication> publish) {
            this.publish = publish;
        }

        public List<HashMap<String, String>> getDevotion() {
            return devotion;
        }

        public void setDevotion(List<HashMap<String, String>> devotion) {
            this.devotion = devotion;
        }

        public List<HashMap<String, Object>> getHki() {
            return hki;
        }

        public void setHki(List<HashMap<String, Object>> hki) {
            this.hki = hki;
        }

        public List<HashMap<String, String>> getTeaching() {
            return teaching;
        }

        public void setTeaching(List<HashMap<String, String>> teaching) {
            this.teaching = teaching;
        }

        public Boolean getDeleted() {
            return isDeleted;
        }

        public void setDeleted(Boolean deleted) {
            isDeleted = deleted;
        }

        public Boolean getSelected() {
            return isSelected;
        }

        public void setSelected(Boolean selected) {
            isSelected = selected;
        }

        public Date getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(Date createdAt) {
            this.createdAt = createdAt;
        }

        public Date getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(Date updatedAt) {
            this.updatedAt = updatedAt;
        }
    }


