package ec;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BuyDataBeans;
import beans.ItemDataBeans;
import dao.BuyDAO;
import dao.BuyDetailDAO;

/**
 * 購入履歴画面
 * @author d-yamaguchi
 *
 */
@WebServlet("/UserBuyHistoryDetail")
public class UserBuyHistoryDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		//BuyDAO.getBuyDataBeansByBuyIdの引数で必要
		//t_buy_detailのbuy_idとt_buyのidは共通しているので、getParameterで受け取る

		//getParameterで取得した値はString型なのでInteger.parseIntでint型に変換してから格納する
		int BuyId = Integer.parseInt(request.getParameter("buy_id"));

		try {
			//BuyIdを引数に渡して購入詳細を表示するためのBuyDataBeansを取得する
			BuyDataBeans BDB = BuyDAO.getBuyDataBeansByBuyId(BuyId);
			request.setAttribute("BDB", BDB);

			// 購入アイテム情報
			ArrayList<ItemDataBeans> IDBList = BuyDetailDAO.getItemDataBeansListByBuyId(BuyId);
			request.setAttribute("IDBList", IDBList);


			request.getRequestDispatcher(EcHelper.USER_BUY_HISTORY_DETAIL_PAGE).forward(request, response);



		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}





	}
}
