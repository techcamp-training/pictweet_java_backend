package in.tech_camp.pictweet.form;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import in.tech_camp.pictweet.CommentForm;
import in.tech_camp.pictweet.ValidGroup1;
import in.tech_camp.pictweet.factory.CommentFormFactory;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class CommentFormUnitTest {

    private Validator validator;

    private CommentForm commentForm;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        commentForm = CommentFormFactory.createComment();
    }
    @Nested
    class コメント投稿ができる場合 {
        @Test
        public void テキストが存在していれば投稿できる () {
          Set<ConstraintViolation<CommentForm>> violations = validator.validate(commentForm, ValidGroup1.class);
          assertEquals(0, violations.size());
        }
    }
    @Nested
    class コメント投稿ができない場合 {
        @Test
        public void テキストが空では投稿できない() {
            commentForm.setText("");

            Set<ConstraintViolation<CommentForm>> violations = validator.validate(commentForm, ValidGroup1.class);
            assertEquals(1, violations.size());
            assertEquals("Text can't be blank", violations.iterator().next().getMessage());
        }
    }
}