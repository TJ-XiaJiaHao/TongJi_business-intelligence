#include <bits/stdc++.h>
#include"parameters.h"
#define __DEBUG__
//#define __FINDINGERROR__
using namespace std;

vector<int> G[MAXN];

#ifdef __DEBUG__
template <typename T>
void print(const vector<T>& v)
{
    for(auto item:v)
        cout << item << ' ';
    cout << endl;
}
#endif // __DEBUG__

void init()
{
    fstream fs("Amazon0601.txt");
    int u,v;
    while(fs >> u >> v)
        G[u].push_back(v);
    fs.close();
}

//return sorted candidate
vector<int> get_candidate(const vector<int>& s,const vector<int>& exclude)
{
    vector<int> answer;
    for(auto u:s)
    {
        for(auto v: G[u])
        {
            if(binary_search(s.begin(),s.end(),v) || binary_search(exclude.begin(),exclude.end(),v))
                continue;
            answer.push_back(v);
        }
    }

    sort(answer.begin(),answer.end());
    answer.resize(unique(answer.begin(),answer.end()) - answer.begin());
    return answer;
}

vector<int> get_candidate(const vector<int>& s,const vector<int>& exclude,vector<double>& outer_score)
{
    vector<int> answer;
    map<int,int> score;

    for(auto u:s)
    {
        for(auto v: G[u])
        {
            if(binary_search(s.begin(),s.end(),v) || binary_search(exclude.begin(),exclude.end(),v))
                continue;
            answer.push_back(v);
            score[v]++;
        }
    }

    sort(answer.begin(),answer.end());
    answer.resize(unique(answer.begin(),answer.end()) - answer.begin());
    outer_score.resize(answer.size());
    for(int i = 0;i < outer_score.size();i++)
        outer_score[i] = score[answer[i]];

    return answer;
}

vector<double> get_inner_score(const vector<int>& candidate)
{
    map<int,vector<int> > out_edge;
    map<int,int> num_to_index;
    vector<double> inner_score(candidate.size(),0);

    for(int i = 0;i < candidate.size();i++)
    {
        int u = candidate[i];
        num_to_index[u] = i;
        for(auto v:G[u])
        {
            auto p = lower_bound(candidate.begin(),candidate.end(),v);
            if(p == candidate.end() || *p != v)
                continue;
            out_edge[u].push_back(*p);
        }
    }

    for(auto item:out_edge)
    {
        const vector<int>& to = item.second;
        double per = 1.0 / to.size();
        for(auto v:to)
            inner_score[num_to_index[v]] += per;

#ifdef __FINDINGERROR__
        int u  = item.first;
        cout << u << ':';
        print(to);
#endif // __FINDINGERROR__
    }
    return inner_score;
}

vector<double> get_outer_score(const vector<int>& candidate,const vector<int>& pre_layer)
{
    vector<double> outer_score;
    get_candidate(pre_layer,{},outer_score);
    return outer_score;
}


vector<double> get_score(const vector<int>& candidate,const vector<int>& pre_layer,int meaningless)
{
    vector<double> outer_score = get_outer_score(candidate,pre_layer);
    return get_score(candidate,outer_score);
}

vector<double> get_score(const vector<int>& candidate,const vector<double>& outer_score)
{
    vector<double> inner_score = get_inner_score(candidate);
    for(int i = 0;i < inner_score.size();i++)
        inner_score[i] = inner_score[i] * INNER_WEIGHT + outer_score[i] * OUTER_WEIGHT;
    return inner_score;
}

//select k items from candidate
vector<int> select(const vector<int>& candidate,const vector<double>& score,int k)
{
    vector<pair<double,int> > score_and_candidate;
    for(int i = 0;i < candidate.size();i++)
        score_and_candidate.emplace_back(score[i],candidate[i]);
    sort(score_and_candidate.begin(),score_and_candidate.end(),greater<pair<double,int> >());
    score_and_candidate.resize(k);

    vector<int> winner;
    for(auto item:score_and_candidate)
        winner.push_back(item.second);
    return winner;
}

vector<int> work(const vector<int>& choices,int desired_num)
{
    vector<double> first_layer_outer_score;
    vector<int> first_layer = get_candidate(choices,vector<int>(),first_layer_outer_score);
    if(desired_num == first_layer.size())
        return first_layer;
    else if(desired_num < first_layer.size())
        return select(first_layer,get_score(first_layer,first_layer_outer_score),desired_num);
    else
    {
        vector<double> second_layer_outer_score;
        vector<int> second_layer = get_candidate(first_layer,choices,second_layer_outer_score);
        if(desired_num >= first_layer.size() + second_layer.size())
        {
            first_layer.insert(first_layer.end(),second_layer.begin(),second_layer.end());
            return first_layer;
        }
        else
        {
            vector<int> second_layer_winner = select(second_layer,get_score(second_layer,second_layer_outer_score),desired_num - first_layer.size());
            first_layer.insert(first_layer.end(),second_layer_winner.begin(),second_layer_winner.end());
            return first_layer;
        }
    }

}
int main()
{
    cout << "Processing input..." <<endl;
    init();
    cout << "Input processing finish." << endl;
#ifdef __DEBUG__
    vector<double> inner_score;
    vector<double> outer_score;
    vector<int> candidate = get_candidate({1},{},outer_score);
    inner_score = get_inner_score(candidate);
    print(candidate);
    print(outer_score);
    print(inner_score);
    print(get_score(candidate,outer_score));
    print(work({1},5));
    print(work({1,2},30));

#endif // __DEBUG__
    return 0;
}
