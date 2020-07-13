package jsj.servlet;

import jsj.dao.ExamRecordDAO;
import jsj.model.ExamRecord;
import jsj.model.Page;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/examRecord/query")
public class ExamRecordQueryServlet extends AbstractBaseServlet{

    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Page p = Page.parse(req);
        List<ExamRecord> records = ExamRecordDAO.query(p);
        return records;
    }
}
