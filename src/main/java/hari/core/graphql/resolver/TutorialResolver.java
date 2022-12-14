package hari.core.graphql.resolver;

import graphql.kickstart.tools.GraphQLResolver;
import hari.core.graphql.models.Author;
import hari.core.graphql.models.Tutorial;
import hari.core.graphql.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class TutorialResolver implements GraphQLResolver<Tutorial> {

    final private AuthorRepository authorRepository;

    public TutorialResolver(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author getAuthor(Tutorial tutorial) {
        return authorRepository.findById(tutorial.getAuthor_id()).orElseThrow(null);
    }
}
