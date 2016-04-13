#include <iostream>
#include <iomanip>
#include <cstdlib>
#include <ctime>

const int ROW = 4;
const int COLUMN = 4;
const int MIN_VALUE = 0;
const int MAX_VALUE = 3;
const int FINAL_VALUE = 2048;
int grid[ROW][COLUMN] = {0} ;

using namespace std;

void initialize();
void display();
void newNumber();
void moveLeft();
void sumLeft();
void moveRight();
void sumRight();
void moveUp();
void sumUp();
void moveDown();
void sumDown();
bool winCheck();
bool fillCheck();

int main()
{
    cout << "Press (a,s,d,w) to move all tiles"<<endl;
    cout << "When two tiles with the same number touch, they merge into one!"<<endl;
    initialize();
    while(true) {
        switch (getchar()) {
            default:
                break;
            case 'a':
                //cout << "left" << endl;
                moveLeft();
                sumLeft();
                newNumber();
                display();
                break;
            case 'w':
                //cout << "up" << endl;
                moveUp();
                sumUp();
                newNumber();
                display();
                break;
            case 'd':
                //cout << "right" << endl;
                moveRight();
                sumRight();
                newNumber();
                display();
                break;
            case 's':
                //cout << "down" << endl;
                moveDown();
                display();
                sumDown();
                newNumber();
                display();
                break;
            case 'q':
                cout << "do you want to exit (y/n)?" << endl;
                char data = getchar();
                if(data=='Y'||data=='y'){
                    exit(0);
                }
                break;
        }
        //reached 2048  => WIN
        //all grids are filled  => better luck next time
        if(winCheck()){
            cout << "---*Congratulations*---" << endl;
            cout << "---------*WIN*----------" << endl;
        } else if(fillCheck()){
            cout << "---*betterluck next time*---" << endl;
        }
    }
    return 0;
}
void initialize(){

    int x1 = (rand() % (MAX_VALUE - MIN_VALUE + 1)) + MIN_VALUE;
    int y1 = (rand() % (MAX_VALUE - MIN_VALUE + 1)) + MIN_VALUE;
    int x2 = (rand() % (MAX_VALUE - MIN_VALUE + 1)) + MIN_VALUE;
    int y2 = (rand() % (MAX_VALUE - MIN_VALUE + 1)) + MIN_VALUE;
    grid[x1][y1] = 2;
    grid[x2][y2] = 2;
    /*
    grid[0][0] = 4;    grid[0][1] = 2;   grid[0][2] = 4;   grid[0][3] = 4;
    grid[1][0] = 16;    grid[1][1] = 8;   grid[1][2] = 4;   grid[1][3] = 0;
    grid[2][0] = 8;    grid[2][1] = 4;   grid[2][2] = 0;   grid[2][3] = 0;
    grid[3][0] = 0;    grid[3][1] = 0;   grid[3][2] = 2;   grid[3][3] = 0;
    */
    display();
}
void display(){
    for(int i=0;i<ROW;i++){
        for(int j=0;j<COLUMN;j++){
            cout << setw(6)<<grid[i][j];
        }
        cout << endl;
    }
}
void newNumber(){
    if(winCheck())  return;
    int count = 0;
    for(int i=0;i<ROW;i++){
        for(int j=0;j<COLUMN;j++){
            if(grid[i][j] != 0){
                count++ ;
            }
        }
    }
    cout<<"count" << count<<endl;
    if(count == 16){
        return;
    }
    unsigned seed = time(0);
    srand(seed);
    int x = (rand() % (MAX_VALUE - MIN_VALUE + 1)) + MIN_VALUE;
    int y = (rand() % (MAX_VALUE - MIN_VALUE + 1)) + MIN_VALUE;
    cout << x << " "<< y << endl;
    while(grid[x][y]!=0){
        x = (rand() % (MAX_VALUE - MIN_VALUE + 1)) + MIN_VALUE;
        y = (rand() % (MAX_VALUE - MIN_VALUE + 1)) + MIN_VALUE;
        cout << x << " "<< y << endl;
    }
    grid[x][y] = 2;
}
bool winCheck(){
    for(int i=0;i<ROW;i++){
        for(int j=0;j<COLUMN;j++){
            if(grid[i][j] == FINAL_VALUE){
                return true;
            }
        }
    }
    return false;
}
void moveLeft(){
    for(int i=0;i<ROW;i++){
        for(int j=0;j<COLUMN;j++){
            if(grid[i][j] == 0){
                for(int k=j;k<COLUMN;k++){
                    if(grid[i][k]!=0){
                        grid[i][j] = grid[i][k];
                        grid[i][k] = 0;
                        break;
                    }
                }
            }
        }
    }
}
void sumLeft(){
    for(int i=0;i<ROW;i++){
        for(int j=0;j<COLUMN-1;j++){
            if(grid[i][j] == grid[i][j+1]){
                grid[i][j] += grid[i][j+1];
                //j++;
                for(int k = j+1;k<COLUMN-1;k++){
                    grid[i][k] = grid[i][k+1];
                }
                grid[i][COLUMN-1] = 0;
            }
        }
    }
}
void moveRight(){
    for(int i=0;i<ROW;i++){
        for(int j=COLUMN-1;j>=0;j--){
            if(grid[i][j] == 0){
                for(int k=j;k>=0;k--){
                    if(grid[i][k]!=0){
                        grid[i][j] = grid[i][k];
                        grid[i][k] = 0;
                        break;
                    }
                }
            }
        }
    }
}
void sumRight(){
    for(int i=0;i<ROW;i++){
        for(int j=COLUMN-1;j>0;j--){
            if(grid[i][j] == grid[i][j-1]){
                grid[i][j] += grid[i][j-1];
                //j--;
                for(int k = j-1;k>0;k--){
                    grid[i][k] = grid[i][k-1];
                }
                grid[i][0] = 0;
            }
        }
    }
}
void moveUp(){
    for(int i=0;i<COLUMN;i++){
        for(int j=0;j<ROW;j++){
            if(grid[j][i] == 0){
                for(int k=j;k<ROW;k++){
                    if(grid[k][i]!=0){
                        grid[j][i] = grid[k][i];
                        grid[k][i] = 0;
                        break;
                    }
                }
            }
        }
    }
}
void sumUp(){
    for(int i=0;i<COLUMN;i++){
        for(int j=0;j<ROW-1;j++){
            if(grid[j][i] == grid[j+1][i]){
                grid[j][i] += grid[j+1][i];
                //j++;
                for(int k = j+1;k<ROW-1;k++){
                    grid[k][i] = grid[k+1][i];
                }
                grid[ROW-1][i] = 0;
                //grid[0][i] = 0;
            }
        }
    }
}
void moveDown(){
    for(int i=0;i<COLUMN;i++){
        for(int j=ROW-1;j>=0;j--){
            if(grid[j][i] == 0){
                for(int k=j;k>=0;k--){
                    if(grid[k][i]!=0){
                        grid[j][i] = grid[k][i];
                        grid[k][i] = 0;
                        break;
                    }
                }
            }
        }
    }
}
void sumDown(){
    for(int i=0;i<COLUMN;i++){
        for(int j=ROW-1;j>0;j--){
            if(grid[j][i] == grid[j-1][i]){
                grid[j][i] += grid[j-1][i];
                //j--;
                for(int k = j-1;k>0;k--){
                    grid[k][i] = grid[k-1][i];
                }
                grid[0][i] = 0;
            }
        }
    }
}
bool fillCheck(){
    if(winCheck())
        return false;
    for(int i=0;i<ROW;i++){
        for(int j=0;j<COLUMN;j++){
            if(grid[i][j] == 0){
                return false;
            }
        }
    }
    for(int i=0;i<ROW;i++){
        for(int j=0;j<COLUMN-1;j++){
            if(grid[i][j] == grid[i][j+1]){
                return false;
            }
        }
    }
    for(int i=0;i<COLUMN;i++){
        for(int j=0;j<ROW-1;j++){
            if(grid[j][i] == grid[j+1][i]){
                return false;
            }
        }
    }
    return true;
}
