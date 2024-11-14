import LowLevelDesign.DesignSplitwise.UserExpenseBalanceSheet;


class User:
    def __init__(self, user_id, user_name):
        self.user_id = user_id
        self.user_name = user_name

    def get_userid(self):
        return self.user_id

    def get_user_expense_balance_sheet():
        return UserExpenseBalanceSheet()


class UserController:

    def __init__(self):
        self.user_list = []

    def add_user(self, user):
        self.user_list.add(user)

    def get_ser(user_id) {

        for user in user_list:
            if user.get_userid() == user_id:
                return user
        return NULL

    def get_all_users():
        return user_list

